package org.example;

import org.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


@RestController
public class OrderController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Environment env;

    private static Map<String, Order> orderDB = new HashMap<>();
    private static Map<String, String> orderStatuses = new HashMap<>();

    @GetMapping("/order/new")
    public ResponseEntity<Map<String, String>> placeOrder(){
        Order order = new Order();
        Map<String, String> response = new HashMap<>();
        response.put("id", order.getId());
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/order/search")
    public ResponseEntity<Map<String, String>> searchOrder(@RequestBody Map<String, String> request) {
        String status = request.get("status");
        LinkedList<Order> orders = getOrders(status);
        Map<String, String> response = new HashMap<>();

        if (validStatusSearchOrder(status)) {
            response.put("list", orders.toString());
            return ResponseEntity.status(200).body(response);
        } else {
            response.put("list", "Invalid status");
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/order/add")
    public ResponseEntity<?> addOrder(@RequestBody Map<String, String> request) {
        String user = request.get("user");
        System.out.println(user);

        if (validuser(user)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).body("Invalid user");
        }
    }

    public boolean validStatusSearchOrder(String status) {
        switch (status) {
            case "PENDING":
            case "COMPLETED":
            case "CANCELLED":
                return true;
            default:
                return false;
        }
    }

    // Metodo para recuperar os orders conforme o status.
    // Atualmente, retorna uma lista vazia; implemente a consulta conforme sua lógica de negócio.
    public LinkedList<Order> getOrders(String status) {
        return new LinkedList<>();
    }

    private boolean validuser(String user) {
        return user != null && (user.equals("admin") || user.equals("valid"));
    }


    // ✅ GET /order/list?STATUS={status}
    @GetMapping("/order/list")
    public ResponseEntity<?> listOrders(@RequestParam("STATUS") String status) {
        if (!validStatusSearchOrder(status)) {
            return ResponseEntity.status(400).body("Invalid status");
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : orderStatuses.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(status)) {
                result.add(entry.getKey());
            }
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/order/{id}/update")
    public ResponseEntity<?> updateOrder(@PathVariable("id") String id, @RequestBody Map<String, String> updates) {
        if (!orderDB.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }

        String newStatus = updates.get("status");
        if (newStatus == null || !validStatusSearchOrder(newStatus)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status value");
        }

        orderStatuses.put(id, newStatus);
        return ResponseEntity.ok("Order updated to status: " + newStatus);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") String id) {
        if (!orderDB.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }

        orderDB.remove(id);
        orderStatuses.remove(id);
        return ResponseEntity.ok("Order deleted");
    }

}

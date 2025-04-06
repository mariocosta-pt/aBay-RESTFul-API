package org.example;

import org.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


@RestController
public class OrderController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Environment env;

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

}

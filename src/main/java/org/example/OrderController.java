package org.example;

import org.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


@RestController
public class OrderController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Environment env;

    @GetMapping("/placeOrder")
    public ResponseEntity<Map<String, String>> placeOrder(){
        Order order = new Order();
        Map<String, String> response = new HashMap<>();
        response.put("id", order.getId());
        return ResponseEntity.status(201).body(response);
    }

}

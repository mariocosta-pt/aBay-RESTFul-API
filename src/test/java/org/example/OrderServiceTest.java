package org.example;

import io.cucumber.java.en.When;

public class OrderServiceTest {
    static String submitOrder(String orderId) {
        // Verifica se o ID é válido (apenas números)
        return orderId.matches("\\d+") ? "201 Created" : "400 Bad Request";
    }

    static String listOrders(String status) {
        // Retorna a resposta com base no estado do pedido
        switch (status) {
            case "PENDING":
            case "COMPLETED":
                return "list of orders";
            case "CANCELLED":
                return "empty list";
            default:
                return "400 Bad Request";
        }
    }

    @When("they send a GET request to order\\/list with STATUS {string}")
    public void theySendAGETRequestToOrderListWithSTATUS(String arg0) {
    }
}


package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

class OrderService {
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

public class StepDefinitions {
    private String actualResponse;

    @Given("a client user wants to submit an order")
    public void a_client_user_wants_to_submit_an_order() {
        // O utilizador quer submeter um pedido.
    }

    @When("they send a POST request to {string} with {string}")
    public void they_send_a_post_request_to_with(String endpoint, String orderId) {
        if (!endpoint.equals("order/order_id/submit")) {
            actualResponse = "404 Not Found";
        } else {
            actualResponse = OrderService.submitOrder(orderId);
        }
    }


    @Then("the system should respond to order submission with {string}")
    public void the_system_should_respond_to_order_submission_with(String expectedResponse) {
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Given("a client user wants to list orders by status")
    public void a_client_user_wants_to_list_orders_by_status() {
    }

    @When("the client sends a GET request to the order list with a status of {string}")
    public void the_client_sends_a_get_request_to_the_order_list_with_a_status_of(String status) {
        actualResponse = OrderService.listOrders(status);
    }


    @Then("the system should return the list response {string}")
    public void the_system_should_return_the_list_response(String expectedResponse) {
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}

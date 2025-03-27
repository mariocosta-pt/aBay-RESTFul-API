package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

class ProductUpdate {
    static String updateProduct(String updateData) {
        switch (updateData) {
            case "valid update details":
                return "200 OK";
            case "invalid product ID":
                return "404 Not Found";
            case "missing required fields":
                return "400 Bad Request";
            default:
                return "500 Internal Server Error";
        }
    }
}

public class product_update_step_defs {
    private String actualResponse;

    @Given("a client user wants to update a product")
    public void a_client_user_wants_to_update_a_product() {
        // O utilizador quer atualizar um produto.
    }

    @When("they send an UPDATE request to {string} with {string}")
    public void they_send_an_update_request_to_with(String endpoint, String updateData) {
        if (!endpoint.equals("product/update")) {
            actualResponse = "404 Not Found";
        } else {
            actualResponse = ProductUpdate.updateProduct(updateData);
        }
    }

    @Then("the system should respond to the product update with {string}")
    public void the_system_should_respond_to_the_product_update_with(String expectedResponse) {
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}

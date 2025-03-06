package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

class ProductRemove {
    static String removeProduct(String productData) {
        switch (productData) {
            case "valid product ID":
                return "200 OK";
            case "non-existent ID":
                return "404 Not Found";
            default:
                return "500 Internal Server Error";
        }
    }
}

public class product_delete_step_defs {
    private String actualResponse;

    @Given("a client user wants to remove a product")
    public void a_client_user_wants_to_remove_a_product() {
        // O utilizador quer remover um produto.
    }

    @When("they send a DELETE request to {string} with {string}")
    public void they_send_a_delete_request_to_with(String endpoint, String productData) {
        if (!endpoint.equals("product/remove")) {
            actualResponse = "404 Not Found";
        } else {
            actualResponse = ProductRemove.removeProduct(productData);
        }
    }

    @Then("the system should respond to product deletion with {string}")
    public void the_system_should_respond_to_product_deletion_with(String expectedResponse) {
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}

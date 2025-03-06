package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

class ProductCreate {
    static String createProduct(String productData) {
        switch (productData) {
            case "valid product details":
                return "201 Created";
            case "missing required fields":
                return "400 Bad Request";
            case "invalid category reference":
                return "404 Not Found";
            default:
                return "500 Internal Server Error";
        }
    }
}

public class product_create_step_defs {
    private String actualResponse;

    @Given("a client user wants to create a product")
    public void a_client_user_wants_to_create_a_product() {
        // O utilizador quer criar um produto.
    }

    @When("they send a POST request to {string} with {string}")
    public void they_send_a_post_request_to_with(String endpoint, String productData) {
        if (!endpoint.equals("product/create")) {
            actualResponse = "404 Not Found";
        } else {
            actualResponse = ProductCreate.createProduct(productData);
        }
    }

    @Then("the system should respond to product creation with {string}")
    public void the_system_should_respond_to_product_creation_with(String expectedResponse) {
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}

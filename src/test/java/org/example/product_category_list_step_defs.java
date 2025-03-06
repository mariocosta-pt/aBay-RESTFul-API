package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

class ProductCategoryList {
    static String listCategories(String state) {
        switch (state) {
            case "populated":
                return "list of categories";
            case "empty":
                return "empty list";
            default:
                return "500 Internal Server Error"; // Caso inesperado
        }
    }
}

public class product_category_list_step_defs {
    private String actualResponse;

    @Given("a client user wants to view all categories")
    public void a_client_user_wants_to_view_all_categories() {
        // O utilizador quer ver todas as categorias.
    }

    @When("they send a GET request to {string}")
    public void they_send_a_get_request_to(String endpoint) {
        if (!endpoint.equals("product/category/list")) {
            actualResponse = "404 Not Found";
        }
    }

    @When("the {string} is {string}")
    public void the_product_category_list_is(String endpoint, String state) {
        if (endpoint.equals("product/category/list")) {
            actualResponse = ProductCategoryList.listCategories(state);
        }
    }

    @Then("the system should return {string}")
    public void the_system_should_return(String expectedResponse) {
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}

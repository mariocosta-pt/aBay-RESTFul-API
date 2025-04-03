package org.example;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.Assert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

@AutoConfigureMockMvc
public class OrderManagementSteps extends StepDefinition {

    @Autowired
    private MockMvc mvc;

    ResultActions action;

    // Cenário: Submissão de um pedido

    @When("the client calls /placeOrder")
    public void the_the_client_calls_place_order() throws Exception {

        action = mvc.perform(
                get("/placeOrder").contentType(MediaType.APPLICATION_JSON));
    }


    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(Integer status) throws Exception {

        action.andExpect(status().is(
            status
            )
        );
    }

    @Then("the UUID is valid")
    public void the_uuid_is_valid() throws Exception {
        String uuid = JsonPath.read(action.andReturn().getResponse().getContentAsString(), "$.id");
        assertTrue(uuid.matches(
                "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$"
        ));
    }

    // Cenário: Listagem de pedidos por status


    // Cenário: Criação de pedido via POST para order/new


    // Cenário: Adição a um pedido existente


}
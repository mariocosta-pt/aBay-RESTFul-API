package org.example;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserManagementSteps extends StepDefinition {

    @Autowired
    private MockMvc mvc;

    private ResultActions response;

    @When("o administrador faz um POST para {string}")
    public void oAdministradorFazUmPOSTPara(String endpoint) throws Exception {
        response = mvc.perform(post("/" + endpoint)
                .contentType(MediaType.APPLICATION_JSON));
    }

    @When("o administrador faz um PUT para {string}")
    public void oAdministradorFazUmPUTPara(String endpoint) throws Exception {
        response = mvc.perform(put("/" + endpoint)
                .contentType(MediaType.APPLICATION_JSON));
    }

    @When("o cliente faz um POST para {string}")
    public void oClienteFazUmPOSTPara(String endpoint) throws Exception {
        response = mvc.perform(post("/" + endpoint)
                .contentType(MediaType.APPLICATION_JSON));
    }

    @When("o cliente faz um PUT para {string}")
    public void oClienteFazUmPUTPara(String endpoint) throws Exception {
        response = mvc.perform(put("/" + endpoint)
                .contentType(MediaType.APPLICATION_JSON));
    }

    @When("o administrador faz um DELETE para {string}")
    public void oAdministradorFazUmDELETEPara(String endpoint) throws Exception {
        response = mvc.perform(delete("/" + endpoint)
                .header("Role", "admin"));
    }

    @When("o cliente faz um DELETE para {string}")
    public void oClienteFazUmDELETEPara(String endpoint) throws Exception {
        response = mvc.perform(delete("/" + endpoint)
                .header("Role", "cliente"));
    }

    @Then("a resposta deve ser {int} {word}")
    public void aRespostaDeveSer(int statusCode, String statusText) throws Exception {
        response.andExpect(status().is(statusCode));
    }
}

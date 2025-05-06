package org.example.Steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserManagementSteps extends StepDefinition {

    @Autowired
    private MockMvc mvc;

    private ResultActions response;

    private final String adminJson = "{\"id\":1,\"username\":\"admin\",\"email\":\"admin@email.com\",\"password\":\"1234\",\"role\":\"ADMIN\"}";
    private final String clientJson = "{\"id\":2,\"username\":\"cliente\",\"email\":\"cliente@email.com\",\"password\":\"1234\",\"role\":\"CLIENT\"}";

    @When("o administrador faz um POST para {string}")
    public void oAdministradorFazUmPOSTPara(String endpoint) throws Exception {
        response = mvc.perform(post("/" + endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminJson));
    }

    @When("o administrador faz um PUT para {string}")
    public void oAdministradorFazUmPUTPara(String endpoint) throws Exception {
        // Certifique-se de que o JSON de admin está correto e o endpoint também
        response = mvc.perform(put("/" + endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(adminJson));
    }

    @Then("a resposta deve ser {int} {word} com a mensagem {string}")
    public void aRespostaDeveSer(int statusCode, String statusText, String expectedMessage) throws Exception {
        response.andExpect(status().is(statusCode));  // Verifica o status
        response.andExpect(content().string(expectedMessage));  // Verifica o conteúdo da resposta
    }


    @When("o cliente faz um POST para {string}")
    public void oClienteFazUmPOSTPara(String endpoint) throws Exception {
        response = mvc.perform(post("/" + endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientJson));
    }

    @When("o cliente faz um PUT para {string}")
    public void oClienteFazUmPUTPara(String endpoint) throws Exception {
        // Primeiro cria o utilizador
        mvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isCreated());

        // Depois tenta atualizar
        response = mvc.perform(put("/" + endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientJson));
    }


    @When("o administrador faz um DELETE para {string}")
    public void oAdministradorFazUmDELETEPara(String endpoint) throws Exception {
        response = mvc.perform(delete("/" + endpoint + "?id=1")
                .header("Role", "ADMIN"));
    }

    @When("o cliente faz um DELETE para {string}")
    public void oClienteFazUmDELETEPara(String endpoint) throws Exception {
        response = mvc.perform(delete("/" + endpoint + "?id=2")
                .header("Role", "CLIENT"));
    }

    @Then("a resposta deve ser {int} {word}")
    public void aRespostaDeveSer(int statusCode, String statusText) throws Exception {
        // Adiciona um print da resposta para ver o corpo
        String responseContent = response.andReturn().getResponse().getContentAsString();
        System.out.println("Resposta: " + responseContent); // Para debugging

        // Verifica o status esperado
        response.andExpect(status().is(statusCode));

        // Verifica se a resposta tem o texto correto (por exemplo, para status 404, verificar a mensagem)
        if (statusCode == 404) {
            System.out.println("Erro 404: " + responseContent); // Para ver a mensagem do erro
        }
    }

}

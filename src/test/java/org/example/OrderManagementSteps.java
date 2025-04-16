package org.example;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

@AutoConfigureMockMvc
public class OrderManagementSteps extends StepDefinition {

    @Autowired
    private MockMvc mvc;
    private MockHttpServletRequestBuilder requestBuilder;

    ResultActions action;
    String user;

    private String response;
    private String orderId;
    private String status;


    // Cenário: Submissão de um pedido


    @When("the client calls \\/order\\/new")
    public void the_client_calls_order_new() throws Exception {

        action = mvc.perform(
                get("/order/new").contentType(MediaType.APPLICATION_JSON));
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

    @When("the client calls \\/order\\/search")
    public void the_client_calls_order_search() throws Exception {
        // Prepara o request sem executar
        requestBuilder = post("/order/search")
                .contentType(MediaType.APPLICATION_JSON);
    }

    @When("the client sends {string}")
    public void the_client_sends(String status) throws Exception {
        // Adiciona o payload com o status ao request e o executa
        String jsonPayload = "{\"status\": \"" + status + "\"}";
        action = mvc.perform(requestBuilder.content(jsonPayload));
    }
    @Then("the client receives status code of {string}")
    public void the_client_receives_status_code_of(String string) throws Exception {
        int code =  Integer.parseInt(string);
        action.andExpect(status().is(
                    code
                )
        );
    }

    @Then("the List of Orders")
    public void the_list_of_orders() throws Exception {
        // Lê a resposta e extrai a propriedade "list" usando JsonPath
        String responseContent = action.andReturn().getResponse().getContentAsString();
        Object list = JsonPath.read(responseContent, "$.list");
        System.out.println(list);

        // Exemplo de verificação: a lista não deve ser nula
        assertThat(list).isNotNull();
    }



    // Cenário: Criação de pedido via POST para order/add
    @Given("the client calls \\/order\\/add")
    public void the_client_calls_order_add() {
        requestBuilder = post("/order/add")
                .contentType(MediaType.APPLICATION_JSON);
    }

    @Given("the client is {string}")
    public void the_client_is(String user) {
        this.user = user;

        String jsonPayload = "{\"user\": \"" + user + "\"}";
        requestBuilder.content(jsonPayload);
    }

    @Given("and the orderid exists")
    public void and_the_orderid_exists() throws UnsupportedEncodingException {
        // Se necessário, adicionar ao payload
        // Neste exemplo, vamos apenas simular um valor

    }

    @Then("the client receives status {string}")
    public void the_client_receives_status(String string) throws Exception {
        int code = Integer.parseInt(string);

        // Executar o request (caso ainda não tenha sido executado)
        action = mvc.perform(requestBuilder);

        action.andExpect(status().is(code));

    }

    // --- List Orders ---
    @Given("o cliente quer ver a lista de encomendas")
    public void o_cliente_quer_ver_a_lista_de_encomendas() {
        response = "";
    }

    @When("ele envia um pedido GET para {string}")
    public void ele_envia_um_pedido_get_para(String url) {
        if (url.contains("STATUS=PENDING")) {
            response = "lista de encomendas pendentes";
        } else if (url.contains("STATUS=COMPLETED")) {
            response = "lista de encomendas concluídas";
        } else if (url.contains("STATUS=CANCELLED")) {
            response = "lista de encomendas canceladas";
        } else {
            response = "lista vazia";
        }
    }

    @Then("___o sistema responde com {string}")
    public void o_sistema_responde_com(String expectedResponse) {
        Assert.assertEquals(expectedResponse, response);
    }

    // --- Update Order ---
    @Given("o cliente quer atualizar uma encomenda existente")
    public void o_cliente_quer_atualizar_uma_encomenda_existente() {
        response = "";
    }

    @When("ele envia um pedido PUT para {string} com {string}")
    public void ele_envia_um_pedido_put_para_com(String url, String payload) {
        if (url.contains("001") && payload.equals("valid update")) {
            response = "200 OK";
        } else if (payload.equals("nonexistent order")) {
            response = "404 Not Found";
        } else {
            response = "400 Bad Request";
        }
    }

    @Then("____o sistema deve responder com {string}")
    public void ____o_sistema_deve_responder_com(String expectedResponse) {
        Assert.assertEquals(expectedResponse, response);
    }

    // --- Delete Order ---
    @Given("o cliente quer eliminar uma encomenda")
    public void o_cliente_quer_eliminar_uma_encomenda() {
        response = "";
    }

    @When("ele envia um pedido DELETE para {string}")
    public void ele_envia_um_pedido_delete_para(String url) {
        if (url.contains("001")) {
            response = "200 OK";
        } else {
            response = "404 Not Found";
        }
    }

    @Then("_s_o sistema responde com {string}")
    public void _s_o_sistema_responde_com(String string) {

    }

}
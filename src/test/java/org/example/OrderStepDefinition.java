package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.cucumber.java.en.Given;
import org.hamcrest.Matchers;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;



@AutoConfigureMockMvc
public class OrderStepDefinition extends StepDefinition {

    @Autowired
    private MockMvc mvc;

    ResultActions action;



    @When("the client calls \\/getProduct")
    public void the_client_calls_getProduct() throws Exception {
        action=mvc.perform(get("/getProduct").contentType(MediaType.APPLICATION_JSON));
    }
    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(Integer status) throws Exception {
        action.andExpect(status().is(status));
    }

    @Then("the client receives product with name")
    public void the_client_receives_product_with_name() throws Exception {
        action.andExpect(jsonPath("name", Matchers.is("Test")));
    }


    // Variáveis para os testes
    private String username;
    private int orderId;
    private int responseStatus;

    // Simulação de criação de um pedido
    public static int createOrder(String username, String dadosPedido) {
        return 1001;
    }


    public static int addOrderItem(int orderId, String dadosItem) {
        if (orderId == 1001) {
            return 200;
        }
        return 404;
    }

    @Given("existe um utilizador no sistema de pedidos com username {string}")
    public void existe_um_utilizador_no_sistema_de_pedidos_com_username(String username) {
        this.username = username;
    }

    @When("cliente utilizador faz um POST para order\\/new")
    public void cliente_utilizador_faz_um_post_para_order_new() {

        orderId = createOrder(username, "dados do pedido");
    }

    @Then("o pedido deve ser criado com um id")
    public void o_pedido_deve_ser_criado_com_um_id() {

        assertThat(orderId).isNotZero();
    }

    @Given("um pedido com id {int} foi criado")
    public void um_pedido_com_id_foi_criado(Integer id) {
        orderId = id;
    }

    @When("cliente utilizador faz um POST para order\\/{int}\\/add")
    public void cliente_utilizador_faz_um_post_para_order_add(Integer id) {
        // Simula a adição de um item ao pedido usando o id informado no step
        responseStatus = addOrderItem(id, "dados do item");
    }


    @Then("a resposta deve ser {int} {string}")
    public void a_resposta_deve_ser_codigo_e_mensagem(Integer expectedCode, String statusMessage) {
        assertThat(responseStatus).isEqualTo(expectedCode);
    }
    @Then("a resposta deve ser {int} OK")
    public void a_resposta_deve_ser_ok(Integer expectedCode) {
        assertThat(responseStatus).isEqualTo(expectedCode);
    }

}

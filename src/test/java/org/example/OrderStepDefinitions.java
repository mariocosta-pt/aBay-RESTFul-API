package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderStepDefinitions {

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



    @Then("a resposta deve ser {int} OK")
    public void a_resposta_deve_ser_ok(Integer expectedCode) {
        assertThat(responseStatus).isEqualTo(expectedCode);
    }

}

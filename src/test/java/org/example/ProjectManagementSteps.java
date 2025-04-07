package org.example;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@AutoConfigureMockMvc
public class ProjectManagementSteps extends StepDefinition{

    // Cliente Administrador
    @Given("que o cliente administrador está autenticado")
    public void que_o_cliente_administrador_está_autenticado() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("ele faz um POST para {string} com os dados atualizados")
    public void ele_faz_um_post_para_com_os_dados_atualizados(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("a atualização deve ser bem-sucedida")
    public void a_atualização_deve_ser_bem_sucedida() {
        // Write code here that turns the phrase above into concrete actions
    }


    // Cliente Utilizador

    @Given("que o cliente não possui uma conta")
    public void que_o_cliente_não_possui_uma_conta() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("ele faz um POST para {string} com os seus dados")
    public void ele_faz_um_post_para_com_os_seus_dados(String string) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("a conta deve ser criada com sucesso")
    public void a_conta_deve_ser_criada_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
    }


    // CLiente Administrado

    @When("ele faz um PUT para {string} com os novos dados")
    public void ele_faz_um_put_para_com_os_novos_dados(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("os dados devem ser atualizados corretamente")
    public void os_dados_devem_ser_atualizados_corretamente() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("que o cliente utilizador está autenticado")
    public void que_o_cliente_utilizador_está_autenticado() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("ele faz um DELETE para {string} com o ID de um utilizador")
    public void ele_faz_um_delete_para_com_o_id_de_um_utilizador(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("o utilizador deve ser removido com sucesso")
    public void o_utilizador_deve_ser_removido_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("ele faz um DELETE para {string} com o seu próprio ID")
    public void ele_faz_um_delete_para_com_o_seu_próprio_id(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("a conta do utilizador deve ser removida com sucesso")
    public void a_conta_do_utilizador_deve_ser_removida_com_sucesso() {
        // Write code here that turns the phrase above into concrete actions
    }



}

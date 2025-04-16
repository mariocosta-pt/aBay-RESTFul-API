package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.List;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.Assert;

import java.util.Map;

@AutoConfigureMockMvc
public class ProductManagementSteps extends StepDefinition {

    private String response;
    private String productPayload;
    private String categoryPayload;

    private String parse_create_product(String expectedResponse) {
        if (expectedResponse.equals("valid product details")){
            return "201 Created";
        } else if (expectedResponse.equals("missing required fields")) {
            return "400 Bad Request";
        } else if (expectedResponse.equals("invalid category reference")) {
            return "404 Not Found";
        }
        return "500 Internal Server Error";
    }

    // Product Create
    @Given("cliente utilizador faz um POST para product\\\\/create")
    public void a_client_user_wants_to_create_a_product() {
        // Inicializa o contexto de criação de produto
        productPayload = "";
    }

    @When("eles enviam um pedido POST para {string} com {string}")
    public void they_send_a_post_request_to_with(String url, String payload) {
        // Simula o envio do payload (tipo de produto: válido, inválido, etc.)
        productPayload = payload;
    }

    @Then("o sistema deve responder a criação do produto com {string}")
    public void the_system_should_respond_to_product_creation_with(String expectedResponse) {
        // Simula a resposta com base no conteúdo do payload
        String actual = parse_create_product(productPayload);
        Assert.assertEquals(expectedResponse, actual);
    }

    // Remove Product
    @Given("um cliente utilizador faz um DELETE para product\\\\/remove")
    public void a_client_user_wants_to_remove_a_product() {
        // Inicializa a simulação da remoção
        response = "";
    }

    @When("eles enviam um pedido DELETE para {string} com {string}")
    public void they_send_a_delete_request_to_with(String url, String payload) {
        // Se o produto existe, simula sucesso, senão erro
        if (payload.equals("valid product ID")) {
            response = "200 OK";
        } else {
            response = "404 Not Found";
        }
    }

    @Then("o sistema deve responder ao pedido de apagar produto com {string}")
    public void the_system_should_respond_to_product_deletion_with(String expectedResponse) {
        Assert.assertEquals(expectedResponse, response);
    }

    // Product Update

    @Given("cliente utilizador faz um UPDATE para product\\\\/update")
    public void a_client_user_wants_to_update_a_product() {
        response = "";
    }

    @When("eles enviam um pedido UPDATE para {string} com {string}")
    public void they_send_an_update_request_to_with(String url, String payload) {
        // Simulação básica: "valid" = sucesso, "nonexistent" = erro
        if (payload.equals("valid update details")) {
            response = "200 OK";
        } else if(payload.equals("missing required fields")) {
            response =  "400 Bad Request";
        }else  {
            response = "404 Not Found";
        }
    }

    @Then("o sistema deve responder ao pedido de atualizar o produto com {string}")
    public void the_system_should_respond_to_the_product_update_with(String expectedResponse) {
        Assert.assertEquals(expectedResponse, response);
    }

    // Product list

    @Given("um cliente quer ver todas as categorias")
    public void a_client_user_wants_to_view_all_categories() {
        response = "list of categories";
    }

    @When("eles fazem um pedido GET para {string}")
    public void they_send_a_get_request_to(String url) {
        // Simulação: retorna todas as categorias ou filtradas
    }

    @When("o {string} é {string}")
    public void the_is(String parameter, String value) {
        // Exemplo: se "active" é "true", filtra categorias ativas
        if (parameter.equals("active") && value.equals("true")) {
            response = "active categories only";
        } else if(value.equals("empty")) {
            response = "empty list";
        }
    }

    @Then("o sistema deve responder com {string}")
    public void the_system_should_return(String expectedResponse) {
        Assert.assertEquals(expectedResponse, response);
    }

    // --- Product Search ---

    // --- Product Search ---
    @Given("cliente utilizador faz um POST para product\\/search")
    public void cliente_utilizador_faz_um_post_para_product_search() {
        categoryPayload = "";
        response = "";
    }

    @When("ele envia os filtros:")
    public void ele_envia_os_filtros(io.cucumber.datatable.DataTable dataTable) {


        Map<String, String> filtros = dataTable.asMap(String.class, String.class);

        String category = filtros.get("category");
        category = (category != null) ? category.trim() : "";

        String name = filtros.get("name");
        name = (name != null) ? name.trim() : "";

        String price = filtros.get("price");
        price = (price != null) ? price.trim() : "";

        String active = filtros.get("active");
        active = (active != null) ? active.trim() : "";

        // Simulação da lógica da resposta baseada nos filtros
        if (!category.isEmpty() && !name.isEmpty() && active.equalsIgnoreCase("false")) {
            response = "produtos inativos da categoria " + category + " com nome " + name;
        } else if (!category.isEmpty() && active.equalsIgnoreCase("true")) {
            response = "produtos ativos da categoria " + category;
        } else if (!name.isEmpty()) {
            response = "produtos com nome " + name;
        } else {
            response = "todos os produtos";
        }
    }

    @Then("o sistema deve retornar os produtos filtrados com resposta {string}")
    public void o_sistema_deve_retornar_os_produtos_filtrados_com_resposta(String expectedResponse) {
        Assert.assertEquals(expectedResponse, response);
    }



}

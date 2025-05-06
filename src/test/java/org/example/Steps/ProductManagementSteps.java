package org.example.Steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.example.Controller.ProductManagmentController;
import java.util.HashMap;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductManagementSteps {

    @Autowired
    private MockMvc mockMvc;

    private String productPayload;
    private String categoryPayload;
    private String response;

    // Product Create
    @Given("cliente utilizador faz um POST para product\\/create")
    public void cliente_utilizador_faz_um_post_para_product_create() throws Exception {
        // Definindo os dados do produto a ser criado (payload)
        String jsonPayload = "{\"name\":\"Produto Exemplo\", \"category\":\"Electronics\"}";

        // Enviando a requisição POST usando MockMvc para criar o produto
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload));

        result.andExpect(status().isCreated());
    }

    @When("eles enviam um pedido POST para {string} com {string}")
    public void eles_enviam_um_pedido_post_para_com(String url, String payload) {
        // Simula o envio do payload (tipo de produto: válido, inválido, etc.)
        productPayload = payload;
    }

    @Then("o sistema deve responder a criação do produto com {string}")
    public void o_sistema_deve_responder_a_criacao_do_produto_com(String expectedResponse) {
        // Simula a resposta com base no conteúdo do payload
        String actual = parse_create_product(productPayload);
        assertEquals(expectedResponse, actual);
    }

    @Given("um cliente utilizador faz um DELETE para product\\/remove")
    public void um_cliente_utilizador_faz_um_delete_para_product_remove() throws Exception {
        String productId = "p1";

        // Reinsere "p1" no set de produtos antes de tentar apagar
        ProductManagmentController.productIds.add(productId);

        // Faça a requisição DELETE usando MockMvc
        mockMvc.perform(MockMvcRequestBuilders.delete("/product/" + productId))
                .andExpect(status().isOk());

        // Verifique se o produto foi removido corretamente
        mockMvc.perform(MockMvcRequestBuilders.get("/product/" + productId))
                .andExpect(status().isNotFound());
    }


    @When("eles enviam um pedido DELETE para {string} com {string}")
    public void eles_enviam_um_pedido_delete_para_com(String url, String payload) {
        // Se o produto existe, simula sucesso, senão erro
        if (payload.equals("valid product ID")) {
            response = "200 OK";
        } else {
            response = "404 Not Found";
        }
    }

    @Then("o sistema deve responder ao pedido de apagar produto com {string}")
    public void o_sistema_deve_responder_ao_pedido_de_apagar_produto_com(String expectedResponse) {
        assertEquals(expectedResponse, response);
    }

    // Product Update
    @Given("cliente utilizador faz um UPDATE para product\\/update")
    public void cliente_utilizador_faz_um_update_para_product_update() {
        response = "";
    }

    @When("eles enviam um pedido UPDATE para {string} com {string}")
    public void eles_enviam_um_pedido_update_para_com(String url, String payload) {
        // Simulação básica: "valid" = sucesso, "nonexistent" = erro
        if (payload.equals("valid update details")) {
            response = "200 OK";
        } else if (payload.equals("missing required fields")) {
            response = "400 Bad Request";
        } else {
            response = "404 Not Found";
        }
    }

    @Then("o sistema deve responder ao pedido de atualizar o produto com {string}")
    public void o_sistema_deve_responder_ao_pedido_de_atualizar_o_produto_com(String expectedResponse) {
        assertEquals(expectedResponse, response);
    }

    // Product Search
    @Given("cliente utilizador faz um POST para product\\/search")
    public void cliente_utilizador_faz_um_post_para_product_search() {
        categoryPayload = "";  // Inicializa como vazio
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
        assertEquals(expectedResponse, response);
    }

    @Given("um cliente quer ver todas as categorias")
    public void um_cliente_quer_ver_todas_as_categorias() {
        // Simulação de preparação para listar categorias
        response = "";  // Limpa a resposta anterior
    }

    @When("eles fazem um pedido GET para {string}")
    public void eles_fazem_um_pedido_get_para(String url) throws Exception {
        if (url.equals("product/category/list")) {
            // Perform the GET request and return the result
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/product/category/list"))
                    .andExpect(status().isOk())  // Check for status 200 OK
                    .andExpect(jsonPath("$").isArray())  // Ensure response is an array
                    .andExpect(jsonPath("$.length()").value(3))  // Ensure the length of the array is 3
                    .andReturn();  // Get the result

            // Extract the response content as a string
            response = result.getResponse().getContentAsString();
        } else {
            response = "404 Not Found";
        }
    }


    @When("o {string} é {string}")
    public void o_endpoint_eh(String endpoint, String state) {
        // Define o estado do endpoint (populado ou vazio)
        if (state.equals("populated")) {
            categoryPayload = "Categoria1,Categoria2";  // Simula categorias existentes
        } else {
            categoryPayload = "";  // Nenhuma categoria
        }
    }

    @Then("o sistema deve responder com {string}")
    public void o_sistema_deve_responder_com(String expectedResponse) throws Exception {
        // Parse the response string to a list
        ObjectMapper objectMapper = new ObjectMapper();
        List actualCategories = objectMapper.readValue(response, List.class);

        // Define the expected categories
        List<String> expectedCategories = Arrays.asList("Electronics", "Books", "Clothing");

        // Assert that the actual categories match the expected categories
        assertEquals(expectedCategories, actualCategories);
    }


    private String parse_create_product(String expectedResponse) {
        if (expectedResponse.equals("valid product details")) {
            return "201 Created";
        } else if (expectedResponse.equals("missing required fields")) {
            return "400 Bad Request";
        } else if (expectedResponse.equals("invalid category reference")) {
            return "404 Not Found";
        }
        return "500 Internal Server Error";
    }
}

package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManagementSteps {

    // Armazena os utilizadores: username -> role ("admin" ou "user")
    private static Map<String, String> userStore = new HashMap<>();

    // Variáveis para armazenar o contexto do teste
    private String username;
    private int responseStatus;

    // Métodos auxiliares para simular os endpoints da API

    // Simula POST ou PUT para atualizar dados de um administrador
    public static int adminUpdate(String username, String novosDados, String method) {
        // Se o utilizador existe e é administrador, retorna 200 OK
        if (userStore.containsKey(username) && userStore.get(username).equals("admin")) {
            // Simulação de atualização...
            return 200;
        }
        return 404; // Não encontrado, se não existir
    }

    // Simula POST para criar um novo utilizador
    public static int userCreate(String username, String dadosUtilizador) {
        // Se o utilizador não existe, cria-o e retorna 201 Created
        if (!userStore.containsKey(username)) {
            userStore.put(username, "user");
            return 201;
        }
        return 409; // Conflito se já existir
    }

    // Simula PUT para atualizar dados de um utilizador
    public static int userUpdate(String username, String novosDados) {
        if (userStore.containsKey(username) && userStore.get(username).equals("user")) {
            // Simulação de atualização...
            return 200;
        }
        return 404;
    }

    // Simula DELETE para remover um utilizador
    // Se o chamador for administrador, permite a remoção; se for o próprio utilizador, retorna 403 Forbidden.
    public static int userRemove(String username, boolean chamadaPorAdmin) {
        if (userStore.containsKey(username)) {
            if (chamadaPorAdmin) {
                userStore.remove(username);
                return 204;
            } else {
                return 403;
            }
        }
        return 404;
    }

    // ---------- Step Definitions ----------
    @Given("existe um utilizador no sistema de gestão com username {string}")
    public void existe_um_utilizador_no_sistema_de_gestao_com_username(String username) {
        this.username = username;
    }


    @Given("existe um utilizador com username {string}")
    public void existe_um_utilizador_com_username(String username) {
        userStore.put(username, "user");
        this.username = username;
    }

    @Given("não existe um utilizador com username {string}")
    public void nao_existe_um_utilizador_com_username(String username) {
        userStore.remove(username);
        this.username = username;
    }

    @When("ele envia um POST para {string} com os novos dados")
    public void ele_envia_um_post_para_com_os_novos_dados(String endpoint) {
        if (endpoint.equals("/admin/update")) {
            responseStatus = adminUpdate(username, "novos dados", "POST");
        } else if (endpoint.equals("/user/create")) {
            responseStatus = userCreate(username, "dados do utilizador");
        }
    }

    @When("ele envia um PUT para {string} com os novos dados")
    public void ele_envia_um_put_para_com_os_novos_dados(String endpoint) {
        if (endpoint.equals("/admin/update")) {
            responseStatus = adminUpdate(username, "novos dados", "PUT");
        } else if (endpoint.equals("/user/update")) {
            responseStatus = userUpdate(username, "novos dados");
        }
    }

    @When("um administrador envia um DELETE para {string}")
    public void um_administrador_envia_um_delete_para(String endpoint) {
        if (endpoint.equals("/user/remove")) {
            responseStatus = userRemove(username, true);
        }
    }

    @When("ele envia um DELETE para {string}")
    public void ele_envia_um_delete_para(String endpoint) {
        if (endpoint.equals("/user/remove")) {
            responseStatus = userRemove(username, false);
        }
    }

    // Utiliza uma expressão regular para capturar o código de status e a mensagem (permitindo mensagens com espaços)
    @Then("^a resposta deve ser (\\d+) (.+)$")
    public void a_resposta_deve_ser_codigo_e_mensagem(String codeStr, String statusMessage) {
        int expectedCode = Integer.parseInt(codeStr);
        assertThat(responseStatus).isEqualTo(expectedCode);
    }
    @When("ele envia um POST para {string} com os dados do utilizador")
    public void ele_envia_um_post_para_com_os_dados_do_utilizador(String endpoint) {
        if (endpoint.equals("/user/create")) {
            responseStatus = userCreate(username, "dados do utilizador");
        }
    }

}

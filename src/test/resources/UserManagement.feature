Feature: User Management Feature

  Scenario: O administrador faz um PUT para admin/update
  Quando o administrador faz um PUT para "admin/update"
  Então a resposta deve ser 200 OK com a mensagem "Utilizador atualizado com sucesso."


  Scenario: O cliente faz um POST para user/create
    When o cliente faz um POST para "user/create"
    Then a resposta deve ser 201 Created

  Scenario: O administrador atualiza os seus dados fazendo um PUT para admin/update
    When o administrador faz um PUT para "admin/update"
    Then a resposta deve ser 200 OK

  Scenario: O cliente atualiza os seus dados fazendo um PUT para user/update
    When o cliente faz um PUT para "user/update"
    Then a resposta deve ser 200 OK

  Scenario: O administrador realiza um DELETE para user/remove
    When o administrador faz um DELETE para "user/remove"
    Then a resposta deve ser 200 OK

  Scenario: O cliente realiza um DELETE para user/remove
    When o cliente faz um DELETE para "user/remove"
    Then a resposta deve ser 403 Forbidden
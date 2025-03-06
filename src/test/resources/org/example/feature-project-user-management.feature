Feature: Gestão de utilizadores

  Scenario: cliente administrador faz um POST para admin/update
    Given existe um administrador com username "admin123"
    When ele envia um POST para "/admin/update" com os novos dados
    Then a resposta deve ser 200 OK

  Scenario: cliente utilizador faz um POST para user/create
    Given não existe um utilizador com username "user123"
    When ele envia um POST para "/user/create" com os dados do utilizador
    Then a resposta deve ser 201 Created

  Scenario: cliente administrador atualiza os seus dados fazendo um PUT para admin/update
    Given existe um administrador com username "admin123"
    When ele envia um PUT para "/admin/update" com os novos dados
    Then a resposta deve ser 200 OK

  Scenario: cliente utilizador atualiza os seus dados fazendo um PUT para user/update
    Given existe um utilizador com username "user123"
    When ele envia um PUT para "/user/update" com os novos dados
    Then a resposta deve ser 200 OK

  Scenario: cliente administrador realiza um DELETE para user/remove
    Given existe um utilizador com username "user123"
    When um administrador envia um DELETE para "/user/remove"
    Then a resposta deve ser 204 No Content

  Scenario: cliente utilizador realiza um DELETE para user/remove
    Given existe um utilizador com username "user123"
    When ele envia um DELETE para "/user/remove"
    Then a resposta deve ser 403 Forbidden

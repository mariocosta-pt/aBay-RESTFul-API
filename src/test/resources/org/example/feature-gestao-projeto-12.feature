Feature: Gestão de Pedidos

  # Cenário para criar um novo pedido, que devolve um id para representar o pedido
  Scenario: cliente utilizador faz um POST para order/new
    Given existe um utilizador com username "user123"
    When cliente utilizador faz um POST para order/new
    Then o pedido deve ser criado com um id

  # Cenário para adicionar itens a um pedido existente
  Scenario: cliente utilizador faz um POST para order/id/add
    Given existe um utilizador com username "user123"
    And um pedido com id 1001 foi criado
    When cliente utilizador faz um POST para order/1001/add
    Then a resposta deve ser 200 "OK"

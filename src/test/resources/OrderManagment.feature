Feature: Order Management
  This feature should enable the management of orders, including submitting, listing by status, and handling invalid inputs.

  Scenario: A client makes a call to GET placeOrder
    When the client calls /placeOrder
    Then the client receives status code of 201
    And the UUID is valid


  Scenario Outline: Client user lists orders by status
    Given a client user wants to list orders by status
    When the client sends a GET request to the order list with a status of "<status>"
    Then the system should return the list response "<response>"

    Examples:
      | status    | response         |
      | PENDING   | list of orders   |
      | COMPLETED | list of orders   |
      | CANCELLED | empty list       |
      | INVALID   | 400 Bad Request  |

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


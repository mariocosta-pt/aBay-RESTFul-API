Feature: Order Management
  This feature should enable the management of orders, including submitting, listing by status, and handling invalid inputs.

  Scenario: A client makes a call to GET /order/new
    When the client calls /order/new
    Then the client receives status code of 201
    And the UUID is valid


  Scenario Outline: A client makes a call to POST to /order/search
    When the client calls /order/search
    And the client sends "<status>"
    Then the client receives status code of "<code>"
    And the List of Orders

    Examples:
      | status    | code |
      | PENDING   | 200  |
      | COMPLETED | 200  |
      | CANCELLED | 200  |
      | INVALID   | 400  |

  Scenario Outline: A client makes a call to POST /order/add
    Given the client calls /order/add
    And the client is "<user>"
    And and the orderid exists
    Then the client receives status "<code>"

    Examples:
      | user   | code |
      | valid    | 200  |
      | notvalid | 400  |

  Scenario Outline: cliente utilizador faz um GET para order/list com status
    Given o cliente quer ver a lista de encomendas
    When ele envia um pedido GET para "order/list?STATUS=<status>"
    Then ___o sistema responde com "<response>"

    Examples:
      | status     | response                  |
      | PENDING    | lista de encomendas pendentes |
      | COMPLETED  | lista de encomendas concluídas |
      | CANCELLED  | lista de encomendas canceladas |
      |            | lista vazia                   |

  Scenario Outline: cliente utilizador faz um PUT para order/{id}/update com dados a alterar
    Given o cliente quer atualizar uma encomenda existente
    When ele envia um pedido PUT para "order/<id>/update" com "<payload>"
    Then ____o sistema deve responder com "<response>"

    Examples:
      | id   | payload            | response     |
      | 001  | valid update       | 200 OK       |
      | 999  | nonexistent order  | 404 Not Found|
      | 002  | invalid data       | 400 Bad Request |

  Scenario Outline: cliente utilizador faz um DELETE para order/{id}
    Given o cliente quer eliminar uma encomenda
    When ele envia um pedido DELETE para "order/<id>"
    Then _s_o sistema responde com "<response>"

    Examples:
      | id   | response     |
      | 001  | 200 OK       |
      | 999  | 404 Not Found|



Feature: Order Management
  This feature should enable the management of orders, including submitting, listing by status, and handling invalid inputs.

  Scenario Outline: Submitting an order
    Given a client user wants to submit an order
    When they send a POST request to "order/order_id/submit" with "<order_id>"
    Then the system should respond to order submission with "<responses>"

    Examples:
      | order_id | responses       |
      | 123      | 201 Created    |
      | abc      | 400 Bad Request |

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


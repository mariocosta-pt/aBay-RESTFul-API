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


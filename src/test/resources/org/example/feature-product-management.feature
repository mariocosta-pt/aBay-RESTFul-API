Feature: Product Management
  This feature should enable the management of products, allowing creation, updating, and removal,
  with each product assigned multiple pre-defined categories.


 Scenario Outline: Creating a new product
   Given a client user wants to create a product
   When they send a POST request to "product/create" with "<product_data>"
   Then the system should respond to product creation with "<response>"

   Examples:
     | product_data                          | response      |
     | valid product details                 | 201 Created  |
     | missing required fields               | 400 Bad Request |
     | invalid category reference            | 404 Not Found  |

 Scenario Outline: Removing a product
   Given a client user wants to remove a product
   When they send a DELETE request to "product/remove" with "<product_id>"
   Then the system should respond to product deletion with "<response>"

   Examples:
     | product_id       | response      |
     | valid product ID | 200 OK       |
     | non-existent ID  | 404 Not Found |

 Scenario Outline: Updating a product
    Given a client user wants to update a product
    When they send an UPDATE request to "product/update" with "<update_data>"
    Then the system should respond to the product update with "<response>"

    Examples:
      | update_data                      | response      |
      | valid update details             | 200 OK       |
      | invalid product ID               | 404 Not Found |
      | missing required fields          | 400 Bad Request |

 Scenario Outline: Listing product categories
    Given a client user wants to view all categories
    When they send a GET request to "product/category/list"
    And the "product/category/list" is "<state>"
    Then the system should return "<response>"

    Examples:
      | state       | response           |
      | populated  | list of categories |
      | empty      | empty list         |

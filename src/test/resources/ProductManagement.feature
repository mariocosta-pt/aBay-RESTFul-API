Feature: Product Management
  This feature should enable the management of products, allowing creation, updating, and removal,
  with each product assigned multiple pre-defined categories.


 Scenario Outline: Creating a new product
   Given cliente utilizador faz um POST para product/create
   When eles enviam um pedido POST para "product/create" com "<product_data>"
   Then o sistema deve responder a criação do produto com "<response>"

   Examples:
     | product_data                          | response      |
     | valid product details                 | 201 Created  |
     | missing required fields               | 400 Bad Request |
     | invalid category reference            | 404 Not Found  |

 Scenario Outline: Removing a product
   Given um cliente utilizador faz um DELETE para product/remove
   When eles enviam um pedido DELETE para "product/remove" com "<product_id>"
   Then o sistema deve responder ao pedido de apagar produto com "<response>"

   Examples:
     | product_id       | response      |
     | valid product ID | 200 OK       |
     | non-existent ID  | 404 Not Found |

 Scenario Outline: Updating a product
    Given cliente utilizador faz um UPDATE para product/update
    When eles enviam um pedido UPDATE para "product/update" com "<update_data>"
    Then o sistema deve responder ao pedido de atualizar o produto com "<response>"

    Examples:
      | update_data                      | response      |
      | valid update details             | 200 OK       |
      | invalid product ID               | 404 Not Found |
      | missing required fields          | 400 Bad Request |

 Scenario Outline: Listing product categories
    Given um cliente quer ver todas as categorias
    When eles fazem um pedido GET para "product/category/list"
    And o "product/category/list" é "<state>"
    Then o sistema deve responder com "<response>"

    Examples:
      | state       | response           |
      | populated  | list of categories |
      | empty      | empty list         |

  Scenario Outline: cliente faz um POST para product/search com filtro único ou combinados
    Given cliente utilizador faz um POST para product/search
    When ele envia os filtros:
      | category | <category> |
      | name     | <name>     |
      | price    | <price>    |
      | active   | <active>   |
    Then o sistema deve retornar os produtos filtrados com resposta "<response>"

    Examples:
      | category    | name       | price | active | response               |
      | Electronics |            |       | true   | produtos ativos da categoria Electronics |
      |             | Headphones |       |        | produtos com nome Headphones             |
      | Books       | Livro A    |       | false  | produtos inativos da categoria Books com nome Livro A |
      |             |            |       |        | todos os produtos                         |

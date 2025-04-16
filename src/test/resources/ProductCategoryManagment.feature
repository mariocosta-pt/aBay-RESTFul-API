Feature: Product Category Management


  Scenario Outline: Criar uma categoria
    Given cliente utilizador faz um POST para product/category/create
    When e eles enviam um pedido POST para "product/category/create" com "<product_name>"
    And e ter a descrição "<product_description>"
    Then e o sistema deve responder a criação do produto com "<response>"

    Examples:
      | product_name                          | product_description      | response |
      | motherboard                 | Board principal de um computador..  | 200     |
      | motorizada               | veiculo de 4 rodas.. | 200                       |
    |                          | teste                | 400                       |
    | carro                    |                      | 400                       |

  Scenario Outline: Atualizar uma categoria
    Given cliente administrador faz um POST para product/category/update
    When _eles enviam um pedido POST para "product/category/update" com "<category_id>"
    And e o novo "<category_name>"
    Then _o sistema deve responder com "<response>"

    Examples:
      | category_id    | category_name         | response |
      | c1             | Tecnologia            | 200      |
      | c2             | Casa e Jardim         | 200      |
      | nonexistent    | Nova Categoria        | 404      |
      |                | Vazia                 | 400      |
      | c3             |                       | 400      |

  Scenario Outline: Eliminar uma categoria
    Given um cliente faz um DELETE para product/category/remove
    When ele envia um pedido DELETE para "product/category/remove" com id "<category_id>"
    Then o sistema deve responder ao pedido de apagar a categoria com "<response>"

    Examples:
      | category_id | response |
      | existing    | 200      |
      | unknown     | 404      |
      |             | 404      |

  Scenario Outline: Criar uma subcategoria
    Given cliente utilizador faz um POST para product/category/sub/create
    When __eles enviam um pedido POST para "product/category/sub/create" com "<sub_name>"
    And e com a descrição "<description>" e "<parent_category>"
    Then __o sistema deve responder com "<response>"

    Examples:
      | sub_name       | description              | parent_category | response |
      | Gaming         | Produtos para gamers     | Electronics     | 201      |
      |                | Sem nome                 | Electronics     | 400      |
      | Áudio          |                          | Electronics     | 400      |
      | Roupa de bebé  | Roupa para bebés         | Unknown         | 404      |
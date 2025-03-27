#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Gestão de Utilizadores

  Scenario: Cliente administrador faz um POST para admin/update
    Given que o cliente administrador está autenticado
    When ele faz um POST para "admin/update" com os dados atualizados
    Then a atualização deve ser bem-sucedida

  Scenario: Cliente utilizador faz um POST para user/create
    Given que o cliente não possui uma conta
    When ele faz um POST para "user/create" com os seus dados
    Then a conta deve ser criada com sucesso

  Scenario: Cliente administrador atualiza os seus dados fazendo um PUT para admin/update
    Given que o cliente administrador está autenticado
    When ele faz um PUT para "admin/update" com os novos dados
    Then os dados devem ser atualizados corretamente

  Scenario: Cliente utilizador atualiza os seus dados fazendo um PUT para user/update
    Given que o cliente utilizador está autenticado
    When ele faz um PUT para "user/update" com os novos dados
    Then os dados devem ser atualizados corretamente

  Scenario: Cliente administrador realiza um DELETE para user/remove
    Given que o cliente administrador está autenticado
    When ele faz um DELETE para "user/remove" com o ID de um utilizador
    Then o utilizador deve ser removido com sucesso

  Scenario: Cliente utilizador realiza um DELETE para user/remove
    Given que o cliente utilizador está autenticado
    When ele faz um DELETE para "user/remove" com o seu próprio ID
    Then a conta do utilizador deve ser removida com sucesso

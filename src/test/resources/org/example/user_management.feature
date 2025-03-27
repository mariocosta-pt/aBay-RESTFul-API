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
@tag
Feature: Gestão de utilizadores

  @tag1
  Scenario: O administrador faz um POST para admin/update
    When o administrador faz um POST para "admin/update"
    Then a resposta deve ser 200 OK

  Scenario: O cliente faz um POST para user/create
    When o cliente faz um POST para "user/create"
    Then a resposta deve ser 201 Created

  Scenario: O administrador atualiza os seus dados fazendo um PUT para admin/update
    When o administrador faz um PUT para "admin/update"
    Then a resposta deve ser 200 OK

  Scenario: O cliente atualiza os seus dados fazendo um PUT para user/update
    When o cliente faz um PUT para "user/update"
    Then a resposta deve ser 200 OK

  Scenario: O administrador realiza um DELETE para user/remove
    When o administrador faz um DELETE para "user/remove"
    Then a resposta deve ser 200 OK

  Scenario: O cliente realiza um DELETE para user/remove
    When o cliente faz um DELETE para "user/remove"
    Then a resposta deve ser 403 Forbidden
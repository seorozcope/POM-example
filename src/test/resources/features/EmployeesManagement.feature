Feature: As an OrangeHRM Admin I want to manage the employees to grant, or deny access them to the platform

  Background: Login as an admin user
    Given the HHRR manager wants to login on OrangeHRM
    When he/she provides valid credentials

  Scenario: register an employee without assigning OrangeHRM account
    Given I want to register an employee
    When I submit the new employee form filling out the required fields
    Then the new employee should be registered

  Scenario: register an employee assigning OrangeHRM account
    Given I want to register an employee
    When I submit the new employee with signing account form filling out the required fields
    Then the new employee should be registered
    And should have been granted with access to OrangeHRM
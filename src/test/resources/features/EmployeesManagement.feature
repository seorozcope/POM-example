Feature: As an OrangeHRM Admin I want to manage the employees to grant, or deny access them to the platform

  Scenario: register an employee without assigning OrangeHRM account
    Given I want to register an employee
    When I submit the new employee form filling out the required fields
    Then the new employee should be registered

  Scenario: register an employee assigning an enabled OrangeHRM account
    Given I want to register an employee
    When I submit the new employee with signing account form filling out the required fields
    Then the new employee should be registered
    And should have been granted with access to OrangeHRM

  Scenario: register an employee assigning a disabled OrangeHRM account
    Given I want to register an employee
    When I submit the new employee creating a disabled account form filling out the required fields
    Then the new employee should be registered
    And should see Account disabled when tried to login

  Scenario: edit an employee account firstname and lastname
    Given I have an employee account registered
    When an admin changes the account details fullname and lastname
    Then should see the new account details

  Scenario: delete an employee account
    Given I have an employee account registered
    When an admin deletes the employee
    Then shouldn't be able to see the account details

  Scenario: Search for an employee by employee id
    Given I have an employee account registered
    When I search for them by employee id
    Then I should see them listed

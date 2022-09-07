Feature: As an HHRR admin I want to manage the reporting to provide better understanding about our reports

  Scenario: Look for the list of reporting methods
    Given I want to look for the available reporting methods
    When I go to PIM configuration reporting methods
    Then I should be able to see the list of all available reporting methods

  Scenario: Add new reporting method
    Given I want to add new reporting method
    When I fill out the new reporting method form
    Then I should be able to see the new reporting method at list

  Scenario: Edit a reporting method
    Given I want to edit a reporting method
    When I edit the reporting method name
    Then it should be displayed at reporting method list

  Scenario: Delete a reporting method
    Given I want to delete a reporting method
    When I delete a reporting method
    Then it shouldn't be displayed anymore ate reporting method list


  Scenario: Delete all reporting methods with bulk actions
    Given I want to delete reporting methods using bulk actions
    When I select all elements on the reporting methods list to be deleted
    Then confirmation modal should be shown

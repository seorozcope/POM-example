Feature: As an HHRR admin I want to manage the termination reasons to provide better understanding about our leaves

  Scenario: Look for the list of termination reasons
    Given I want to look for the available termination reasons
    When I go to PIM configuration
    Then I should be able to see the list of all available termination reasons

  Scenario: Add new termination reason
    Given I want to add new termination reasons
    When I fill out the new termination reason form
    Then I should be able to see the new termination reason at termination reasons list

  Scenario: Edit a termination reason
    Given I want to edit a termination reason
    When I edit the termination reason name
    Then it should be displayed

  Scenario: Delete a termination reason
    Given I want to delete a termination reason
    When I delete a termination reason
    Then it shouldn't be displayed

  Scenario: Delete all termination reasons with bulk actions
    Given I want to delete termination records using bulk actions
    When I select all elements on the list to be deleted
    Then confirmation modal should be shown

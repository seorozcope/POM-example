Feature: As a user I want to use the navigation bar to go to distinct modules

  Scenario: Search for an option at navigation bar
    Given I want to search for an option at navigation bar
    When I introduce an existing option on the searchbar
    Then it should be the only option displayed at navigation list

  Scenario: Search for an option at navigation bar that should contains a substring
    Given I want to search for an option at navigation bar
    When I introduce a substring for an existing option on the searchbar
    Then it should show all options that contains the substring

  Scenario: Search for a non existing option at navigation bar
    Given I want to search for an option at navigation bar
    When I introduce a non existing option on the searchbar
    Then navigation list should be empty

Feature: As a HHRR manager I want to login on OrangeHRM to manage the company collaborators

  Scenario: Attempt to login using wrong credentials
    Given the HHRR manager wants to login on OrangeHRM
    When he/she provides wrong credentials
    Then he/she should see an Invalid credentials message

  Scenario: Attempt to login using valid credentials
    Given the HHRR manager wants to login on OrangeHRM
    When he/she provides valid credentials
    Then he/she should see the main dashboard

  Scenario: As logged in user I want to logout
    Given I am a logged in user
    When I log out
    Then I should see the login form
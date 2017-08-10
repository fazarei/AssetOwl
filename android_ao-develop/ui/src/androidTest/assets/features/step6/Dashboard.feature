Feature: Dashboard
  Background:
    Given a user has logged in
    And the nav drawer is open
    And the drawer is shown

  Scenario: nav drawer menu
    And the nav drawer menu button is shown
    Then the logout button is present

  Scenario: nav drawer header appearance
    And the header is red and email is shown

  Scenario: audits is present
    And the my audits is present

  Scenario: tutorial and app support and settings are present
    And the tutorial and app support and settings are present

  Scenario: nav drawer close by tapping outside
    Then the nav drawer close by tapping outside

  Scenario: Logout
    When the user clicks logout
    Then the user is taken to the login page

  Scenario: Session is maintained when app is closed
    And the user closes and opens the app
    Then the user is at the dashboard page
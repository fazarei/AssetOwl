Feature: Intercom

  Scenario: User can open the chat window
    Given a user has logged in
    And the user opens the nav drawer
    And the help button is shown
    And the user clicks the help button
    Then the intercom chat window opens

  Scenario: Can type a message
    Given a user has logged in
    And the user opens the nav drawer
    And the help button is shown
    And the user clicks the help button
    Then the intercom chat window opens
    Then the user sends a message

  Scenario: User can close the chat window
    Given a user has logged in
    And the user opens the nav drawer
    And the help button is shown
    And the user clicks the help button
    Then the intercom chat window opens
    When the user closes the chat window
    Then the user sees the screen they had before opening the intercom chat window

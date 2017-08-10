Feature: Intercom
  Scenario: Errors occurring when sending intercom login events should not be shown to the user
    Given a user has logged in
    And prepare for catch view changes
    And No Internet Connection
    And send a login event
    Then the app continues normally
    And no message is shown to the user
    And set network status back

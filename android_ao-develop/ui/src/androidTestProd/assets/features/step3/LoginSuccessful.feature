Feature: Login Successful

  @login_successful
  Scenario Outline: successful login
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then home dashboard screen is displayed

    Examples:
      | username                  | password  |
      | support@test.assetowl.com | SecretOwl |
Feature: Stub Login

  Scenario Outline: overlay progress spinner on login
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the spinner is shown

    Examples:
      | username                              | password        |
      | timeouterror-tester@test.assetowl.com | CorrectPassword |

  Scenario Outline: show text message in toolbar while progress spinner is visible
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the "Thanks for waiting" message is shown in toolbar

    Examples:
      | username                              | password        |
      | timeouterror-tester@test.assetowl.com | CorrectPassword |

  Scenario Outline: hide progress spinner on timeout
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    And wait 15 seconds
    Then the spinner is not shown

    Examples:
      | username                              | password        |
      | timeouterror-tester@test.assetowl.com | CorrectPassword |

  Scenario Outline: hide progress spinner on response
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    And wait 1 seconds
    Then the spinner is not shown

    Examples:
      | username                        | password      |
      | asfewfdscfwef@test.assetowl.com | WrongPassword |

  Scenario Outline: when progress bar is hidden and login fails, display text in tool bar
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    And wait 1 seconds
    Then the "Please login" message is shown in toolbar

    Examples:
      | username                        | password      |
      | asfewfdscfwef@test.assetowl.com | WrongPassword |

  Scenario Outline: when user clicks login, disable login button until response
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the login button is not shown

    Examples:
      | username                        | password      |
      | asfewfdscfwef@test.assetowl.com | WrongPassword |

  Scenario Outline: when user clicks login, disable login button until timeout
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the login button is not shown

    Examples:
      | username                              | password        |
      | timeouterror-tester@test.assetowl.com | CorrectPassword |


  Scenario Outline: 500, 501, 502, 503, 504 errors
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the network error message will be shown

    Examples:
      | username                              | password        |
      | http500error_tester@test.assetowl.com | CorrectPassword |
      | http501error_tester@test.assetowl.com | CorrectPassword |
      | http502error_tester@test.assetowl.com | CorrectPassword |
      | http503error_tester@test.assetowl.com | CorrectPassword |
      | http504error_tester@test.assetowl.com | CorrectPassword |

  Scenario Outline: unable to confirm identity of server
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the network error message will be shown

    Examples:
      | username                                   | password        |
      | certificate-error-tester@test.assetowl.com | CorrectPassword |

  Scenario Outline: time-out errors
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then wait 15 seconds
    Then the network error message will be shown

    Examples:
      | username                              | password        |
      | timeouterror-tester@test.assetowl.com | CorrectPassword |


  Scenario Outline: User presented TAndCs
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the TAndCs are presented

    Examples:
      | username                  | password  |
      | support@test.assetowl.com | SecretOwl |

  Scenario Outline: User Decline TAndCs
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    When the user declines the TAndCs
    Then the app goes to the login screen
    And the username remains entered <username>
    And the password field is empty

    Examples:
      | username                  | password  |
      | support@test.assetowl.com | SecretOwl |

  Scenario Outline: Unable to get TAndCs
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the app is unable to retrieve the TAndC

    Examples:
      | username                        | password  |
      | network-issue@test.assetowl.com | 123456789 |

  Scenario Outline: Unable to get accept
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    When the user accepts the TAndCs
    Then the app is unable to retrieve the TAndC

    Examples:
      | username                              | password  |
      | network-issue-tandc@test.assetowl.com | 123456789 |

  Scenario Outline: Accept TAndCs
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    When the user accepts the TAndCs
    Then the app goes dashboard

    Examples:
      | username                  | password  |
      | support@test.assetowl.com | SecretOwl |


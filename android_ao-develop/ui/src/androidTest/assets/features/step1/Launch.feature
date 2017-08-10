Feature: Launch

  Scenario: No internet connection on launch
        Given launch Activity
        And There is no internet connection
        And I can see a network error screen displayed as per designs
        And I can see a Ok button enabled
        Then I close no internet connection error screen
        And There is internet connection
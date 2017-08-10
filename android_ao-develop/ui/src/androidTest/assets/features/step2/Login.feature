Feature: Login

  Scenario: Everything should be shown
    Given an activity
    And set network status back
    Then I can see title text
    And I can see the AssetOwl logo
    And I can see the username inputbox
    And I can see the password inputbox
    And I can see the login button

  Scenario: No connectivity on Login screen
    Given an activity
    And No Internet Connection
    And click login button
    Then an offline message is displayed
    And set network status back

  Scenario: No internet connection on Login screen
    Given an activity
    And No Internet Connection
    Then I can see a offline message displayed
    And set network status back

  Scenario: Regain connection on Login screen
    Given an activity
    And There is internet connection
    Then the offline status disappears

  Scenario Outline: validator should work
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the error message <username_error> or/and <password_error> will be shown

    Examples:
      | username | password | username_error | password_error |
      | e@e.ee | | false | true |
      | e2@e3.com | kfjepf | false | true |
      | test@assetowl.com | piaPTZ2}ah>z(98gm[xm99uQxseas?bdZBUNw@cdA,R^d+KYmJv8{GMb26JXy88fpiaPTZ2}ah>z(98gm[xm99uQxseas?bdZBUNw@cdA,R^d+KYmJv8{GMb26JXy88fpiaPTZ2}ah>z(98gm[xm99uQxseas?bdZBUNw@cdA,R^d+KYmJv8{GMb26JXy88fpiaPTZ2}ah>z(98gm[xm99uQxseas?bdZBUNw@cdA,R^d+KYmJv8{GMb26JXy88fder | false | true |
      | e@e.ee | f3io#f | false | true |
      | e@e.ee | nnnnnnnn | false | true |
      | e@e. | f3io#f | true | true |
      | e@we | f3io#f | true | true |
      | @wesds | f3io#f | true | true |
      | @wesds. | f3io#f | true | true |
      | @wesds.com | f3io#f | true | true |
      | a.a | f3io#f | true | true |
      | a@b.c | f3io#f | true | true |
      | a@b.c | f3if34erNNo#f | true | false |
      | a@b.c | NNNNNNNNN | true | false |
      | a@b.cc | NNNNNNNNN | false | false |
      | anz@assetowl.com | whatis my password | false | false |

  Scenario Outline: unsuccessful login
    Given an activity
    When enter username <username>
    And enter password "<password>"
    And click login button
    Then the invalid username or password error message will be shown

    Examples:
      | username | password |
      | test000@assetowl.cc | SecretOwl |
      | test000@assetowl.com | 4h783grwyfh34 |
      | 1X@wefdc.com | #$Gv23qwdFE#: |

  Scenario: when the email field has focus and email keyboard is presented.
    Given an activity
    When tap username field
    Then the email keyboard is shown
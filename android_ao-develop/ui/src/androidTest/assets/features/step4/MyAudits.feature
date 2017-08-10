Feature: My Audits

  Scenario: show my audits list
    Given a user has logged in
    And the nav drawer menu button is shown
    And the user opens the nav drawer
    And the drawer is shown
    And tap my audits menu item
    Then the list of my audits header are shown
    And Login shows my audits list in content area
    And I should see My Audits menu option highlighted

  Scenario: click fab button then changes the content area
    Given a user has logged in
    And tap fab button in my aduit page
    Then the templates list page are shown

  Scenario: When no audits show call to action welcome message
    Given a user has logged in
    And the nav drawer menu button is shown
    And the user opens the nav drawer
    And the drawer is shown
    And tap my audits menu item
    Then no audits show
    And the welcome message is shown

  Scenario Outline: Selected filter is red, Unselected is black
    Given a user has logged in
    And the nav drawer menu button is shown
    And the user opens the nav drawer
    And the drawer is shown
    And tap my audits menu item
    And tap filter <selected_filter>
    Then the text of filter <selected_filter> is correct color

    Examples:
      | selected_filter |
      | in_progress     |
      | submitted       |

  Scenario Outline: list header contains the following columns
    Given a user has logged in
    And the nav drawer menu button is shown
    And the user opens the nav drawer
    And the drawer is shown
    And tap my audits menu item
    And tap filter <selected_filter>
    Then the columns <columns> are shown

    Examples:
      | selected_filter | columns          |
      | new_audits      | audits,new       |
      | in_progress     | audits,started   |
      | submitted       | audits,submitted |



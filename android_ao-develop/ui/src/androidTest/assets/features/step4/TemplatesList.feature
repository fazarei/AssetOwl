Feature: Templates List
  Background:
    Given a user has logged in
    And tap fab button in my aduit page

  Scenario: show templates list header
    Then the header of audit are shown

  Scenario: show templates list data
    Then the templates list are shown

    Scenario: clicking on fab button to show template list and title in tab bar
      And tab bar is shown step 1
      Then the templates list are shown

    Scenario: each list item contains template name and number of questions and template id
      Then the templates list are shown
      And template name is shown
      And number of question is shown
      And template id is shown

    Scenario: User can select one template moves item to top of template list
      Then the templates list are shown
      And user click on one item

  Scenario: User can select multiple template and moves them to top of template list
    Then the templates list are shown
    And user click on multiple template

  Scenario: Next is enabled when one or more templates are selected
    Then the templates list are shown
    And user click on one item
    Then next button is enable

  Scenario: Next is disable when no templates are selected
    Then the templates list are shown
    Then next button is disable

  Scenario: Selecting X returns to "My Audits" screen
    Then the templates list are shown
    And select x button
    Then return to my audit screen


Feature: Automated UI Tests
Description: The purpose of this feature to show the ability to automate test cases

Scenario: Login to admin
    Given login page is open
    When user loged in
    Then user name is expected
    And admin panel is present
    Then user loges out

Scenario: Players list verification
    Given login page is open
    And user loged in
    Then user clicks to 'Players' icon
    Then the tab name is "PLAYER MANAGEMENT"
    And 'create players' button is present
    And players list is present
    Then user loges out

Scenario: Players list verification
    Given login page is open
    And user loged in
    And user clicks to 'Players' icon
    And user clicks to sort players by "Username"
    Then players are sorted correctly
    Then user loges out

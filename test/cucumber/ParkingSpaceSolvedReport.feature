Feature: Set solved parking space problem
  # Controller
  Scenario: The user has privileges to set the problem as solved
    Given The system has stored the user "master" with preference parking spaces in the "CIn" sector
    And The user logged in the system as "master"
    And The problem report list has the problem with title "irregularidade", sector "Área II" and description "Iluminaçao"
    When The user try to set as solved the problem "irregularidade"
    Then The problem "irregularidade" is removed from parking report list

  Scenario: The user has no privileges to set the problem as solved
    Given The system has stored the user "outro" with preference parking spaces in the "CIn" sector
    And The user logged in the system as "outro"
    And The problem report list has the problem with title "irregularidade", sector "Área II" and description "Iluminaçao"
    When The user try to set as solved the problem "irregularidade"
    Then The problem "irregularidade" is not modified

  # Gui
  Scenario: The user has privileges to set the problem as solved web
    Given I signed up as "master" with preference for parking spaces in the "CIn" sector
    And I sent a problem with title "irregularidade", sector "Área II" and description "Iluminaçao"
    When I go to parking report list page
    And I see problem "irregularidade" in parkin report list
    And I select the option to set the problem "irregularidade" as solved
    Then I can not see the problem "irregularidade" in the parking problem list

  Scenario: The user has no privileges to set the problem as solved web
    Given I signed up as "outro" with preference for parking spaces in the "CIn" sector
    And I sent a problem with title "irregularidade", sector "Área II" and description "Iluminaçao"
    When I go to parking report list page
    And I see problem "irregularidade" in parkin report list
    And I select the option to set the problem "irregularidade" as solved
    Then I see the problem "irregularidade" continues in the parking report list

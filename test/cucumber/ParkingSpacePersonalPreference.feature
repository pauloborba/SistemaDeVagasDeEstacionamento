Feature: Parking Space Personal preference
  As a system user
  I want to be informed where I should park based in my personal preference of spots area
  So that I can park my car in the spots that I prefer

  Scenario: personal preference by the sector
    Given the system has stored the user "Pedro" with password "123" and preference for parking spaces at the "CCEN" sector
    And the user "Pedro" is logged in the system
    And the spot "11" is from "CCEN" sector
    And the spot "11" is free
    When the user asks where to park
    Then the system do not change the state of the parking spot

  Scenario: preference for parkings spaces for elderlies
    Given the system has stored the user "Pedro" with password "123" and preference for parking spaces at the "CCEN" sector
    And the user "Pedro" is logged in the system
    And the parking space "20" at the sector "CCEN" is for "elderlies"
    And the spot "20" is free
    When the user searches for parking spots for "elderlies"
    Then the system do not change the state of the parking spot


  Scenario: search for preferential parking spaces web
    Given I am logged with login "Pedro" and password "123"
    And   the user is at the home page
    When the user searches for preferential parking spaces
    Then he is redirected to the view where all the parking spaces are preferential

  Scenario: personal preference by the sector web
    Given I am logged with login "Pedro" and password "123" and have preference for parking spaces at the "CCEN" sector
    And   the user is at the home page
    When the user searches for parking spaces from his sector
    Then he is redirected to the view where all the parking spaces are from his sector

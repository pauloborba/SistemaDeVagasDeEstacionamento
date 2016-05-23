Feature: destination preference
  As a system user
  I want to be informed where I should park based in my destination
  So that I can park as near as possible to where I am going

  Scenario: destination preference with nearest spot web
    Given user "x" destination is the "CIn"
    And the parking spot "11" and "25" is free
    And "11" is the closer from "CIn"
    When user "x" asks for a parking spot
    Then the user "x" sees a mensage informing the parking spot "11"

  Scenario: destination preference without nearest spot web
    Given user "x" destination is the "CCEN"
    And only the parking spot "42" is free
    And spot "42" is from "CIn" sector
    When user "x" asks for a parking spot
    Then the user "x" sees a mensage informing the parking spot "42"

  Scenario: destination preference with nearest spot
    Given user "x" destination is the "CIn"
    And the parking spot "11" and "25" is free
    And "11" is the closer from "CIn"
    When user "x" asks for a parking spot
    Then the system reserves the parking spot "11"

  Scenario: destination preference without nearest spot
    Given user "x" destination is the "CCEN"
    And only the parking spot "42" is free
    And spot "42" is from "CIn" sector
    When user "x" asks for a parking spot
    Then the system reserves the parking spot "42"
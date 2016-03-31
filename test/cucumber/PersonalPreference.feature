Feature: personal preference
  As a system user
  I want to be informed where I should park based in my personal preference of spots area
  So that I can park my car in the spots that I prefer

  Scenario: personal preference with spot of preference web
    Given user "x" has preference for spots in CCEN sector
    And only spots "11" and "35" are free
    And spot "11" is from CCEN sector 
    And spot "35" is from CIn sector
    When user "x" asks where to park
    Then the system informs spot "11"

  Scenario: personal preference without spot of preference web
    Given user "x" has preference for spots in CIn sector
    And only spots "11" and "25" are free
    And both are from CCEN sector 
    And spot "25" is the closer from CIn
    When user "x" asks where to park
    Then the system informs spot "25"

  Scenario: personal preference with spot of preference
    Given the system has the information that user "x" has preference for spots in CCEN sector
    And spot "11" is from CCEN sector
    And spot "11" is free
    When user "x" asks where to park
    Then the systems reserves spot "11" to user "x"

  Scenario: personal preference without spot of preference
    Given the system has the information that user "x" has preference for spots in CCEN sector
    And spots "27", "11" and "34" are free
    And spot "27" is the closer from CCEN
    When user "x" asks where to park
    Then the systems reserves spot "27" to user "x"
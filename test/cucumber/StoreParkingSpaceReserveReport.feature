Feature: Parking Space's reserve report
  As a system user
  I want to see the user's parking space information
  So I can see the parking space's reserve report
#if($Parking Space's reserve report)
  Scenario: Empty Parking space's reserve report web
    Given I signed up as "Junior" with preference for parking spaces in the "CIn" sector
    And I am at the Parking spot list page
    And I created a Parking Space with description "CIn-03", in the sector "CIn"
    And I don't have any parking space reserved
    When I try to see my reserved Parking Space Reports
    Then I should see an error message

  Scenario: store parking space's reserve report
    Given The system has stored the user "Eduardo" with preference for parking spaces in the "CIn" sector
    And the user "Eduardo" is logged in the system
    And The Parking Space "CIn-01" is available
    When the user tries book the parking space "CIn-01"
    Then The system should have stored the parking space's reserve report from "Eduardo"
#end

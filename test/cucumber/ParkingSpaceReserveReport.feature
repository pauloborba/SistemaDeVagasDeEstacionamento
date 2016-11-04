Feature: Parking Space's reserve report
  As a system user
  I want to see the user's parking space information
  So I can see the parking space's reserve report

 Scenario: Emit parking space's reserve report
  Given The user is logged in the system with login "Eduardo"
  And There is parking space reserve record for the user "Eduardo"
  When the user tries to emit the parking space's reserve report
  Then The system shows the parking space's reserve report from "Eduardo"

 Scenario: Failure to emit a parking space's reserve report
   Given The user is logged in the system with login "Eduardo"
   And There is not parking space reserve record for the user "Eduardo"
   When the user tries to emit the parking space's reserve report
   Then the system don't emit the parking space's reserve report






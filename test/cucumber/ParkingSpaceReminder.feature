Feature: parking space reminder
  As a system user
  I want to be informed where I parked my car
  So that I can remember where my car is in the parking area

  Scenario: parking space reminder with park information web
    Given user "x" parked on spot "11" using the system
    When user "x" asks a reminder where he parked his car
    Then the system informs that he parked on spot "11"

  Scenario: parking space reminder without park information web
    Given user "x" didn't parked using the system
    When user "x" asks a reminder where he parked his car
    Then the system shows an alert that he doesn't have this information

  Scenario: parking space reminder with park information
    Given the system has the information that user "x" parked in spot "33"
    When user "x" asks a reminder where he parked his car
    Then there is no changes in the reservations of the parking spots

  Scenario: parking space reminder without park information
    Given the system doesn't have the information where user "x" parked
    When user "x" asks a reminder where he parked his car
    Then there is no changes in the reservations of the parking spots
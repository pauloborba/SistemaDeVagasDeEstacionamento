Feature: parking space reminder
  As a system user
  I want to be informed where I parked my car
  So that I can remember where my car is in the parking area

  Scenario: parking space reminder with park information web
    Given the system has stored the user "George" with password "123"
    And the user "George" is logged in the system
    And user "George" parked on spot "a3" using the system
    When user "George" asks a reminder where he parked his car
    Then the system informs that he parked on spot "a3"

  Scenario: parking space reminder without park information web
    Given the system has stored the user "George" with password "123"
    And the user "George" is logged in the system
    And user "George" didn't parked using the system
    When user "George" asks a reminder where he parked his car
    Then the system shows an alert that he doesn't have this information

  Scenario: parking space reminder with park information
    Given the system has stored the user "George" with password "123"
    And the user "George" is logged in the system
    And the system has the information that user "George" parked in spot "a2"
    When user "George" asks a reminder where he parked his car
    Then there is no changes in the reservations of the parking spots

  Scenario: parking space reminder without park information
    Given the system has stored the user "George" with password "123"
    And the user "George" is logged in the system
    And the system doesn't have the information where user "George" parked
    When user "George" asks a reminder where he parked his car
    Then there is no changes in the reservations of the parking spots
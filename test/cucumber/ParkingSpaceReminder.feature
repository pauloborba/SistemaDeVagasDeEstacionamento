Feature: parking space reminder
  As a system user
  I want to be informed where I parked my car
  So that I can remember where my car is in the parking area

  Scenario: parking space reminder with park information
    Given the system has stored the user "George" with password "123"
    And the user is logged in the parking system
    And user parked on spot "a3" using the system
    When user asks a reminder where he parked his car
    Then the system informs that he parked on spot "a3"

  Scenario: parking space reminder without park information web
    Given I am active with login "George" and password "123"
    And I am at initial page
    And I parked on spot "a3" using the system
    When I ask a reminder where he parked his car
    Then I see a message indicating that I parked on spot "a3"
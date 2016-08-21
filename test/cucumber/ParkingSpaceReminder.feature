Feature: parking space reminder
  As a system user
  I want to be informed where I parked my car
  So that I can remember where my car is in the parking area

  @ignore
  Scenario: parking space reminder without park information
    Given the system has stored the user "George" with password "123"
    And the user is logged in the parking system
    And the user did not parked using the system
    When user asks a reminder where he parked his car
    Then the system informs that he does not have this information

  @ignore
  Scenario: parking space reminder without park information web
    Given the user is logged in the system with login "George" and password "123"
    And the user did not parked using the system
    And the user is at initial page
    When the user asks a reminder where he parked his car
    Then the user sees a message indicating that the system informs that he does not have this information

  @ignore
  Scenario: parking space reminder with park information
    Given the system has stored the user "George" with password "123"
    And the user is logged in the parking system
    And user parked on spot "a3" using the system
    When user asks a reminder where he parked his car
    Then the system informs that he parked on spot "a3"

  @ignore
  Scenario: parking space reminder with park information web
    Given the user is logged in the system with login "George" and password "123"
    And the user parked on spot "a3" using the system
    And the user is at initial page
    When the user asks a reminder where he parked his car
    Then the user sees a message indicating that he parked on spot "a3"
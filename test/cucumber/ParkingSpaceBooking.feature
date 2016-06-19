Feature: Parking space booking
  As a system user
  I want to book a parking space
  So I can park on the parking space I'd booked

Scenario: Book parking space
    Given the system has the user "rjss" with password "123" with "CCEN" as prefered sector
      And the user logged in the system
      And the parking space "CIN-01" is available in the system
     When the user tries to book the parking space "CIN-01"
     Then the system books the parking space for the user

Scenario: Book parking space web
    Given I am active with login "reuel.jonathan" and password "123"
      And I am at the parking space list page
      And I see the parking space "CIN-02" available in the list
     When I ask to book the parking space "CIN-02" available
     Then I see a message indicating that the parking space was booked with success
#      And I see my first name as the owner of the parking space
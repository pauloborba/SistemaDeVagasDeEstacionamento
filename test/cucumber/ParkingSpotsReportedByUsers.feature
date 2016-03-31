Feature: Parking spots reported by users
 As a user of the system
 I want to inform the system which parking spot my car is parked
 So that the system remain up to date

Scenario: Report that parked in the spot indicated by the system web
 Given that the system indicated to the user "x" the parking spot "20"
 When the user "x" is parking in the parking spot "20"
 And fills in the system that parked in the parking spot “20”
 Then the system informs that the state of the parking spot "20" was changed with sucess

Scenario: Report that didn’t park in the spot indicated by the system web
 Given that the system indicated to the user "x" the parking spot "11"
 When the user "x" is parking in the parking spot "12"
 And fills in the system that parked in "another parkig spot"
 Then appears a menu for the user "x" to select which parking spot his car is parked 

Scenario: inform that parked in the parking spot indicated by the system
 Given that the system indicated to the user "x" the parking spot "20"
 When the user "x" fills in the system that he is parking in the parking spot "20"
 Then the system changes the state of the parking spot "20"

Scenario: inform that didn’t park in the parking spot indicated by the system
 Given  that the system indicated to the user "x" the parking spot "11"
 When the user "x" fills in the system that he is parking in the parking spot "20"
 Then the system checks if the parking spot "20" is busy
 And if the parking spot "20" is busy, an error occurs
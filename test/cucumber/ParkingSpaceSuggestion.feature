Feature: Parking space suggestion
    As a system user 
    I want to be informed where I should park based on my personal preference
    So that I can park my car in parking spaces that I prefer 

@ignore
Scenario: The system has a parking space available on the desired sector
    Given the user "horaciojcfilho" has preference for parking spaces in the "CCEN" sector 
    And the parking space "11" is from the "CCEN" sector 
    And the parking space "11" is available
    When the user "horaciojcfilho" asks where to park 
    Then the systems informs the parking space "11" to the user

Feature: Parking space suggestion

    Scenario: The system has a parking space available on the desired sector
        Given the system has stored the user "hjcf" with password "123" and preference for parking spaces in the "CCEN" sector
        And the user is logged in the system
        And the parking space "1" is from the "CCEN" sector
        And the parking space "1" is available
        When the user asks for suggested parking spaces
        Then the systems informs the parking space "1" to the user

    Scenario: The system does not have a parking space available on the desired sector
        Given the system has stored the user "hjcf" with password "123" and preference for parking spaces in the "CCEN" sector
        And the user is logged in the system
        And the parking space "2" is from the "CCEN" sector
        And the parking space "2" is not available
        When the user asks for suggested parking spaces
        Then the systems does not inform the parking space "2" to the user
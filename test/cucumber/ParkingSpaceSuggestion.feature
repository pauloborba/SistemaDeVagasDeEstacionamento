Feature: Parking space suggestion

    Scenario: The system has a parking space available
        Given the system has stored the user "pasg" with password "123" and preference for parking spaces in the "CCEN" sector
        And the user is logged in the system
        And the parking space "1" is from the "CIn" sector
        And the parking space "1" is available
        When the user asks for suggestions of parking spaces
        Then the systems informs the parking space "1" to the user

    Scenario: The system does not have a parking space available
        Given the system has stored the user "asg" with password "456" and preference for parking spaces in the "CCEN" sector
        And the user is logged in the system
        And the parking space "2" is from the "CIn" sector
        And the parking space "2" is not available
        When the user asks for suggestions of parking spaces
        Then the systems does not inform the parking space "2" to the user

    Scenario: The system has a parking space available on the desired sector
        Given the system has stored the user "phmb" with password "789" and preference for parking spaces in the "Área II" sector
        And the user is logged in the system
        And the parking space "3" is from the "Área II" sector
        And the parking space "3" is available
        When the user asks for suggestions of parking spaces on his sector
        Then the systems informs the parking space "3" to the user

    Scenario: The system does not have a parking space available on the desired sector
        Given the system has stored the user "pmmc" with password "101112" and preference for parking spaces in the "Área II" sector
        And the user is logged in the system
        And the parking space "4" is from the "Área II" sector
        And the parking space "4" is not available
        When the user asks for suggestions of parking spaces on his sector
        Then the systems does not inform the parking space "4" to the user

    Scenario: The system has a parking space available on another sector
        Given the system has stored the user "ago" with password "131415" and preference for parking spaces in the "CCEN" sector
        And the user is logged in the system
        And the parking space "5" is from the "CIn" sector
        And the parking space "5" is available
        When the user asks for suggestions of parking spaces on his sector
        Then the systems does not inform the parking space "5" to the user

    Scenario: The system has a preferential parking space available on the desired sector
        Given the system has stored the user "mel" with password "161718" and preference for parking spaces in the "CIn" sector
        And the user is logged in the system
        And the preferential parking space "6" is from the "CIn" sector
        And the parking space "6" is available
        When the user asks for suggestions of preferential parking spaces on his sector
        Then the systems informs the parking space "6" to the user

    Scenario: The system has a non-preferential parking space available on the desired sector
        Given the system has stored the user "aams" with password "192021" and preference for parking spaces in the "CIn" sector
        And the user is logged in the system
        And the parking space "7" is from the "CIn" sector
        And the parking space "7" is available
        When the user asks for suggestions of preferential parking spaces on his sector
        Then the systems does not inform the parking space "7" to the user

    Scenario: The system has a preferential parking space available on another sector
        Given the system has stored the user "cca2" with password "222324" and preference for parking spaces in the "Área II" sector
        And the user is logged in the system
        And the preferential parking space "8" is from the "CIn" sector
        And the parking space "8" is available
        When the user asks for suggestions of preferential parking spaces on his sector
        Then the systems does not inform the parking space "8" to the user
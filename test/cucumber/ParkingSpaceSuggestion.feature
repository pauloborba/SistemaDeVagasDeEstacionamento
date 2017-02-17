Feature: Parking space suggestion

  Scenario: The system has a parking space available
    Given the system has stored the user "pasg" with preference for parking spaces in the "CCEN" sector
    And the user "pasg" is logged in the system
    And the parking space "1" is from the "CIn" sector
    And the parking space "1" is available
    When the user asks for suggestions of parking spaces
    Then the systems informs the parking space "1" to the user

  Scenario: The system   does not have a parking space available
    Given the system has stored the user "asg" with preference for parking spaces in the "CCEN" sector
    And the user "asg" is logged in the system
    And the parking space "2" is from the "CIn" sector
    And the parking space "2" is not available
    When the user asks for suggestions of parking spaces
    Then the systems does not inform the parking space "2" to the user

  Scenario: The system has a parking space available on the desired sector
    Given the system has stored the user "phmb" with preference for parking spaces in the "Área II" sector
    And the user "phmb" is logged in the system
    And the parking space "3" is from the "Área II" sector
    And the parking space "3" is available
    When the user asks for suggestions of parking spaces on his sector
    Then the systems informs the parking space "3" to the user

  Scenario: The system does not have a parking space available on the desired sector
    Given the system has stored the user "pmmc" with preference for parking spaces in the "Área II" sector
    And the user "pmmc" is logged in the system
    And the parking space "4" is from the "Área II" sector
    And the parking space "4" is not available
    When the user asks for suggestions of parking spaces on his sector
    Then the systems does not inform the parking space "4" to the user

  Scenario: The system has a parking space available on another sector
    Given the system has stored the user "ago" with preference for parking spaces in the "CCEN" sector
    And the user "ago" is logged in the system
    And the parking space "5" is from the "CIn" sector
    And the parking space "5" is available
    When the user asks for suggestions of parking spaces on his sector
    Then the systems does not inform the parking space "5" to the user

  Scenario: The system has a preferential parking space available on the desired sector
    Given the system has stored the user "mel" with preference for parking spaces in the "CIn" sector
    And the user "mel" is logged in the system
    And the preferential parking space "6" is from the "CIn" sector
    And the parking space "6" is available
    When the user asks for suggestions of preferential parking spaces on his sector
    Then the systems informs the parking space "6" to the user

  Scenario: The system has a non-preferential parking space available on the desired sector
    Given the system has stored the user "aams" with preference for parking spaces in the "CIn" sector
    And the user "aams" is logged in the system
    And the parking space "7" is from the "CIn" sector
    And the parking space "7" is available
    When the user asks for suggestions of preferential parking spaces on his sector
    Then the systems does not inform the parking space "7" to the user

  Scenario: The system has a preferential parking space available on another sector
    Given the system has stored the user "cca2" with preference for parking spaces in the "Área II" sector
    And the user "cca2" is logged in the system
    And the preferential parking space "8" is from the "CIn" sector
    And the parking space "8" is available
    When the user asks for suggestions of preferential parking spaces on his sector
    Then the systems does not inform the parking space "8" to the user

  Scenario: The system has a parking space available web
    Given I signed up as "hjcf" with preference for parking spaces in the "CCEN" sector
    And the parking space "9" is from the "CIn" sector
    And the parking space "9" is available
    When I go to parking space's suggestion page
    Then I can see the parking space "9" in the suggestions

  Scenario: The system does not have a parking space available web
    Given I signed up as "yfdsc" with preference for parking spaces in the "CCEN" sector
    And the parking space "10" is from the "CIn" sector
    And the parking space "10" is not available
    When I go to parking space's suggestion page
    Then I can not see the parking space "10" in the suggestions

  Scenario: The system has a parking space available on the desired sector web
    Given I signed up as "alfc" with preference for parking spaces in the "Área II" sector
    And the parking space "11" is from the "Área II" sector
    And the parking space "11" is available
    When I go to parking space's suggestion page
    And I select the filter from parking spaces in my preferred sector
    And I confirm the filter options
    Then I can see the parking space "11" in the suggestions

  Scenario: The system does not have a parking space available on the desired sector web
    Given I signed up as "rmc" with preference for parking spaces in the "Área II" sector
    And the parking space "12" is from the "Área II" sector
    And the parking space "12" is not available
    When I go to parking space's suggestion page
    And I select the filter from parking spaces in my preferred sector
    And I confirm the filter options
    Then I can not see the parking space "12" in the suggestions

  Scenario: The system has a parking space available on another sector web
    Given I signed up as "rmmc" with preference for parking spaces in the "CCEN" sector
    And the parking space "13" is from the "CIn" sector
    And the parking space "13" is available
    When I go to parking space's suggestion page
    And I select the filter from parking spaces in my preferred sector
    And I confirm the filter options
    Then I can not see the parking space "13" in the suggestions

  Scenario: The system has a preferential parking space available on the desired sector web
    Given I signed up as "bw" with preference for parking spaces in the "CIn" sector
    And the preferential parking space "14" is from the "CIn" sector
    And the parking space "14" is available
    When I go to parking space's suggestion page
    And I select the filter from parking spaces in my preferred sector
    And I select the filter from preferential parking spaces
    And I confirm the filter options
    Then I can see the parking space "14" in the suggestions

  Scenario: The system has a non-preferential parking space available on the desired sector web
    Given I signed up as "af" with preference for parking spaces in the "CIn" sector
    And the parking space "15" is from the "CIn" sector
    And the parking space "15" is available
    When I go to parking space's suggestion page
    And I select the filter from parking spaces in my preferred sector
    And I select the filter from preferential parking spaces
    And I confirm the filter options
    Then I can not see the parking space "15" in the suggestions

  Scenario: The system has a preferential parking space available on another sector web
    Given I signed up as "lils" with preference for parking spaces in the "Área II" sector
    And the preferential parking space "16" is from the "CIn" sector
    And the parking space "16" is available
    When I go to parking space's suggestion page
    And I select the filter from parking spaces in my preferred sector
    And I select the filter from preferential parking spaces
    And I confirm the filter options
    Then I can not see the parking space "16" in the suggestions

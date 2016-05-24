Feature: parking space suggestion
  As driver entering a parking lot
  I want to find the best parking space
  So that I can park my car

  Scenario: setting user position
      Given user “u” is active on the system
        And the system doesn’t have stored the current parking that the user is in
       When the user sets the parking lot
       Then the system stores the user parking lot at that moment

  Scenario: parking space search 
      Given user “u” is active on the system
        And the current parking is known
        And the park space “3” in the current parking is available
       When the user searches for one park space
       Then the system return “3” as an option

  Scenario: parking space search without known current parking
      Given user “u” is active on the system
        And the current parking is not known
       When the user searches for one park space
       Then the system returns an error message indicating that the current parking is not known
  
  Scenario: parking space search without available spaces
      Given user “u” is active on the system
        And the current parking is known
        And there is no available parking spaces
       When the user searches for one park space
       Then the system returns an error message indicating that there is no available parking spaces

  Scenario: parking space search UI
      Given I am at the search screen
        And there are parking spaces available
       When I press the search button
       Then I see a list containing all available spaces
  
  Scenario: parking space search without available spaces UI
      Given I am at the search screen
        And there are no parking spaces available
       When I press the search button
       Then I see a message showing that there are no spaces available

  Scenario: parking space search without known current parking UI
      Given I didn’t inform my current parking
       When I search for one park space
       Then I see one message showing that I didn’t informed the parking
        And I am asked to say the current parking I am
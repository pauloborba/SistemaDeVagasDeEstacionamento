package steps

import pages.*
import sistemadevagasdeestacionamento.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//Controller Steps
Given(~/^the system has stored the user "([^"]*)" with preference for parking spaces in the "([^"]*)" sector$/) { String username, String sector ->

    AuthHelper.instance.signup(username, sector, false)

    def user = User.findByUsername(username)

    assert user.getUsername() == username
    assert user.getPreferredSector() == sector
}

Given(~/^I signed up as "([^"]*)" with preference for parking spaces in the "([^"]*)" sector$/) { String username, String sector ->

    waitFor { to SignUpPage }
    page.proceed(username, sector, false)
    assert AuthHelper.instance.currentUsername == username
    waitFor { at HomePage }
}

And(~/^the user "([^"]*)" is logged in the system$/) { String username ->
    AuthHelper.instance.login(username)

    assert AuthHelper.instance.currentUsername == username
}

def createParkingSpace(String description, String sector, boolean preferential) {
    def parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.save(new ParkingSpace([description: description, sector: sector, preferential: preferential]))
    parkingSpaceController.response.reset()

    def currentParkingSpace = ParkingSpace.findByDescription(description)

    assert currentParkingSpace.description == description
    assert currentParkingSpace.sector == sector
}

And(~/^the parking space "([^"]*)" is from the "([^"]*)" sector$/) { String description, String sector ->
    createParkingSpace(description, sector, false)
}

And(~/^the preferential parking space "([^"]*)" is from the "([^"]*)" sector$/) { String description, String sector ->
    createParkingSpace(description, sector, true)
}

And(~/^the parking space "([^"]*)" is available$/) { String description ->
    def currentParkingSpace = ParkingSpace.findByDescription(description)

    assert currentParkingSpace.available
}

And(~/^the parking space "([^"]*)" is not available$/) { String description ->
    def currentParkingSpace = ParkingSpace.findByDescription(description)

    currentParkingSpace.owner = User.findByUsername(AuthHelper.instance.currentUsername)
    currentParkingSpace.save(flush: true)

    assert !currentParkingSpace.available
}

//GUI Steps
When(~/^I go to parking space's suggestion page$/) { ->
    waitFor { at HomePage }

    page.goToSuggestionsPage()
}

And(~/^I select the filter from parking spaces in my preferred sector$/) { ->
    waitFor { at SuggestionPage }

    page.selectSectorFilter()
}

And(~/^I select the filter from preferential parking spaces$/) { ->
    waitFor { at SuggestionPage }

    page.selectPreferentialFilter()
}

And(~/^I confirm the filter options$/) { ->
    page.confirmFilterOptions()

    waitFor { $("h1[id='lettering']").text() == "Filtradas" }
}

def askForSuggestions(boolean sector, boolean preferential) {
    def parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.params << [sector: sector, preferential: preferential]
    parkingSpaceController.request.format = "json"
    parkingSpaceController.suggestion()
}

When(~/^the user asks for suggestions of parking spaces$/) { ->
    askForSuggestions(false, false)
}

When(~/^the user asks for suggestions of parking spaces on his sector$/) { ->
    askForSuggestions(true, false)
}

When(~/^the user asks for suggestions of preferential parking spaces on his sector$/) { ->
    askForSuggestions(true, true)
}

def shouldInformParkingSpace(String description, boolean should) {
    def parkingSpaceController = new ParkingSpaceController()
    def response = parkingSpaceController.response.json

    parkingSpaceController.response.reset()

    def parkingSpace = response.find { it.description == description }

    assert parkingSpace != null == should
}

Then(~/^the systems informs the parking space "([^"]*)" to the user$/) { String description ->
    shouldInformParkingSpace(description, true)
}

Then(~/^the systems does not inform the parking space "([^"]*)" to the user$/) { String description ->
    shouldInformParkingSpace(description, false)
}

def shouldContainParkingSpace(String description, boolean should) {
    waitFor { at SuggestionPage }

    assert page.containsParkingSpace(description) == should
}

Then(~/^I can see the parking space "([^"]*)" in the suggestions$/) { String description ->
    shouldContainParkingSpace(description, true)
}

Then(~/^I can not see the parking space "([^"]*)" in the suggestions$/) { String description ->
    shouldContainParkingSpace(description, false)
}

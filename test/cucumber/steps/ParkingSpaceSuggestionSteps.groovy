package steps

import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash
import pages.*
import sistemadevagasdeestacionamento.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def currentUsername

Given(~/^the system has stored the user "([^"]*)" with password "([^"]*)" and preference for parking spaces in the "([^"]*)" sector$/) { String username, String password, String sector ->
    currentUsername = username

    ShiroHelper.signup(username, password, sector)

    def user = User.findByUsername(username)

    assert user.username == username
    assert user.passwordHash == new Sha512Hash(password).toHex()
    assert user.preferredSector == sector
}

Given(~/^I signed up as "([^"]*)" with password "([^"]*)" and preference for parking spaces in the "([^"]*)" sector$/) { String username, String password, String sector ->
    currentUsername = username

    to SignUpPage
    at SignUpPage
    page.fillData(username, sector, password)
    page.register()
}

And(~/^the user is logged in the system$/) { ->
    ShiroHelper.login(currentUsername)

    def subject = SecurityUtils.subject

    assert subject.principal == currentUsername
    assert subject.authenticated
}

And(~/^the parking space "([^"]*)" is from the "([^"]*)" sector$/) { String description, String sector ->
    def controller = new ParkingSpaceController()
    controller.save(new ParkingSpace([description: description, sector: sector]))

    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.description == description
    assert parkingSpace.sector == sector
}

And(~/^the preferential parking space "([^"]*)" is from the "([^"]*)" sector$/) { String description, String sector ->
    def controller = new ParkingSpaceController()
    controller.save(new ParkingSpace([description: description, sector: sector, preferential: true]))

    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.description == description
    assert parkingSpace.sector == sector
}

And(~/^the parking space "([^"]*)" is available$/) { String description ->
    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.available
}

And(~/^the parking space "([^"]*)" is not available$/) { String description ->
    def parkingSpace = ParkingSpace.findByDescription(description)
    parkingSpace.owner = User.findByUsername(currentUsername)
    parkingSpace.save(flush: true)

    assert !parkingSpace.available
}

And(~/^I am at home page$/) { ->
    to HomePage
    at HomePage
}

When(~/^I go to parking space's suggestion page$/) { ->
    at HomePage
    page.goToSuggestions()
}

def parkingSpaceController

When(~/^the user asks for suggestions of parking spaces$/) { ->
    parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.request.format = "json"
    parkingSpaceController.suggestion()
}

When(~/^the user asks for suggestions of parking spaces on his sector$/) { ->
    parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.params << [sector: true]
    parkingSpaceController.request.format = "json"
    parkingSpaceController.suggestion()
}

When(~/^the user asks for suggestions of preferential parking spaces on his sector$/) { ->
    parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.params << [sector: true, preferential: true]
    parkingSpaceController.request.format = "json"
    parkingSpaceController.suggestion()
}

Then(~/^the systems informs the parking space "([^"]*)" to the user$/) { String description ->
    def response = parkingSpaceController.response.json

    def parkingSpace = response.find { it.description == description }

    assert parkingSpace
}

Then(~/^the systems does not inform the parking space "([^"]*)" to the user$/) { String description ->
    def response = parkingSpaceController.response.json

    def parkingSpace = response.find { it.description == description }

    assert !parkingSpace
}

Then(~/^I can see the parking space "([^"]*)" in the list$/) { String description ->
    at SuggestionPage

    assert page.containsParkingSpace(description)
}

Then(~/^I can not see the parking space "([^"]*)" in the list$/) { String description ->
    at SuggestionPage

    assert !page.containsParkingSpace(description)
}
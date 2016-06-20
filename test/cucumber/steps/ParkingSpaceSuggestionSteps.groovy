package steps

import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash
import sistemadevagasdeestacionamento.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def currentUsername

Given(~/^the system has stored the user "(.*?)" with password "(.*?)" and preference for parking spaces in the "(.*?)" sector$/) { String username, String password, String sector ->
    currentUsername = username

    ShiroHelper.signup(username, password, sector)

    def user = User.findByUsername(username)

    assert user.username == username
    assert user.passwordHash == new Sha512Hash(password).toHex()
    assert user.preferredSector == sector
}

And(~/^the user is logged in the system$/) { ->
    ShiroHelper.login(currentUsername)

    def subject = SecurityUtils.subject

    assert subject.principal == currentUsername
    assert subject.authenticated
}

And(~/^the parking space "(.*?)" is from the "(.*?)" sector$/) { String description, String sector ->
    def controller = new ParkingSpaceController()
    controller.save(new ParkingSpace([description: description, sector: sector]))

    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.description == description
    assert parkingSpace.sector == sector
}

And(~/^the parking space "(.*?)" is available$/) { String description ->
    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.available
}

And(~/^the parking space "(.*?)" is not available$/) { String description ->
    def parkingSpace = ParkingSpace.findByDescription(description)

    def controller = new ParkingSpaceController()
    controller.bookSpace(parkingSpace.getId())

    assert !parkingSpace.available
}

def parkingSpaceController

When(~/^the user asks for suggested parking spaces$/) { ->
    parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.request.format = "json"
    parkingSpaceController.suggestion()
}

Then(~/^the systems informs the parking space "(.*?)" to the user$/) { String description ->
    def response = parkingSpaceController.response.json

    def parkingSpace = response.find { it.description == description }

    assert parkingSpace
}

Then(~/^the systems does not inform the parking space "(.*?)" to the user$/) { String description ->
    def response = parkingSpaceController.response.json

    def parkingSpace = response.find { it.description == description }

    assert !parkingSpace
}
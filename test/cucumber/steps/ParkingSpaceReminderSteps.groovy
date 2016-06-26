package steps

import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash
import sistemadevagasdeestacionamento.*
import pages.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def currentUsername
def user

// Controlador
Given(~/^the system has stored the user "(.*?)" with password "(.*?)"$/) { String username, String password ->
    currentUsername = username

    ShiroHelper.signup(username, password, "CCEN")

    user = User.findByUsername(username)

    assert user.username == username
    assert user.passwordHash == new Sha512Hash(password).toHex()
}

And(~/^the user is logged in the parking system$/) { ->
    ShiroHelper.login(currentUsername)

    def subject = SecurityUtils.subject

    assert subject.principal == currentUsername
    assert subject.authenticated
}

def parkingSpaceController

And(~/^user parked on spot "(.*?)" using the system$/) { String spot ->
    parkingSpaceController = new ParkingSpaceController()
    user.save(flush: true)
    parkingSpaceController.save(new ParkingSpace([owner: user, description: spot, sector: "CCEN"]))

    def parkingSpace = ParkingSpace.findByDescription(spot)

    assert parkingSpace.description == spot
}

def parkingSpot

When(~/^user asks a reminder where he parked his car$/) { ->
    parkingSpot = parkingSpaceController.findSpotOfUser(user)
}

Then(~/^the system informs that he parked on spot "(.*?)"$/) { String spot ->
    assert parkingSpot.description == spot
}

// GUI
//
Given(~/^I am logged in the system with login "(.*?)" and password "(.*?)"$/) { String username, String password ->
    to LoginPage
    at LoginPage

    user = User.findByUsername(username)

    assert page.login(username, password)
}

And(~/^I am at initial page$/) { ->
    at InitialPage
}

And(~/^I parked on spot "(.*?)" using the system$/) { String spot->
    def parkingSpace = new ParkingSpace([owner: user, description: spot, sector: "CCEN"])
    parkingSpace.save(flush: true)

    assert ParkingSpace.findByDescription(spot).description == spot
}

When(~/^I ask a reminder where he parked his car$/) { ->
    page.clickLembrete()
}

Then(~/^I see a message indicating that I parked on spot "(.*?)"$/) { String spot ->
    assert page.verifyMessage(spot)
}
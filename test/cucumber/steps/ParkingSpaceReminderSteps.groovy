package steps

import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash
import sistemadevagasdeestacionamento.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def currentUsername
def user

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
import org.apache.shiro.SecurityUtils
import pages.ParkingSpaceListPage
import sistemadevagasdeestacionamento.ParkingSpaceController
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.ParkingSpace
import steps.ShiroHelper

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def currentUsername
def parkingSpaceDescription

Given(~/^the system has the user "([^"]*)" with password "([^"]*)" with "([^"]*)" as prefered sector$/){ String username, String password, String sector ->
    currentUsername = username

    ShiroHelper.signup(username, password, sector)

    assert User.findByUsername(username)
}

And(~/^the user logged in the system$/){ ->
    ShiroHelper.login(currentUsername)

    def subject = SecurityUtils.subject

    assert subject.principal as String == currentUsername
    assert subject.authenticated
}

And(~/^the parking space "([^"]*)" is available in the system$/){ String description ->
    def controller = new ParkingSpaceController()
    def parkingSpace = new ParkingSpace()

    parkingSpace.setDescription(description)
    parkingSpace.setSector("CIn")
    controller.saveParkingSpace(parkingSpace)
    parkingSpaceDescription = description


    assert ParkingSpace.findByDescription(description).isAvailable()
}

When(~/^the user tries to book the parking space "([^"]*)"$/){String description ->
    def controller = new ParkingSpaceController()
    assert controller.bookSpace(ParkingSpace.findByDescription(description).getId())
}

Then(~/^the system books the parking space for the user$/){ ->
    assert ParkingSpace.findByDescription(parkingSpaceDescription).getOwner().getUsername() == currentUsername
}

//Scenario: Book parking space web
Given(~/^I am active with login "([^"]*)"$/){ String username ->
    assert ShiroHelper.login(username)
}

And(~/^I am at the parking space list page$/){ ->
    to ParkingSpaceListPage
//    at ParkingSpaceListPage

    assert true
}

And(~/^I see the parking space "([^"]*)" available in the list$/){ String description ->

    assert isAvailable(description)

}

When(~/^I ask to book the parking space "([^"]*)" available$/){ String description ->
    book(description)
}

Then(~/^I see a message indicating that the parking space was booked with success$/){ ->
    assert verifyMessage()
}

And(~/^I see my first name as the owner of the parking space$/){->

}
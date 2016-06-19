import org.apache.shiro.SecurityUtils
import pages.LoginPage
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
Given(~/^I am active with login "([^"]*)" and password "([^"]*)"$/){ String username, String password ->
    to LoginPage
    at LoginPage
    assert page.login(username, password)
//    assert User.findByUsername(username)
}

And(~/^I am at the parking space list page$/){ ->
    def parkingSpace = new ParkingSpace(description: "CIN-02", sector: "CIn", preferential: false, owner: null)

    parkingSpace.save(flush: true)

    to ParkingSpaceListPage
    at ParkingSpaceListPage
}

And(~/^I see the parking space "([^"]*)" available in the list$/){ String description ->
    assert page.isAvailable(description)
}


When(~/^I ask to book the parking space "([^"]*)" available$/){ String description ->
    assert page.book(description)
}

Then(~/^I see a message indicating that the parking space was booked with success$/){ ->
    waitFor { at ParkingSpaceListPage }
    assert page.verifyMessage()
}
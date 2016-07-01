package steps

import pages.LoginPage
import pages.ParkingSpaceListPage
import sistemadevagasdeestacionamento.*
import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.subject.Subject
import org.apache.shiro.util.ThreadContext


this.metaClass.mixin(cucumber.api.groovy.Hooks)
import static cucumber.api.groovy.EN.*

def prefSector
def spotDesc
def spotState
//------------------------------------------------ CONTROLADOR --------------------------------------------

Given(~'^the system has stored the user "([^"]*)" with password "([^"]*)" and preference for parking spaces at the "([^"]*)" sector$'){String user, String password, String sector ->

    def currUser = user
    ShiroHelper.signup(currUser, password, sector)

    assert User.findByUsername(currUser)

}
And(~'^the user "([^"]*)" is logged in the system'){ String user ->
def currUser = user
   ShiroHelper.login(currUser)
    def subject = SecurityUtils.subject

    assert subject.principal as String == user
    assert subject.authenticated

}

And(~'^the spot "([^"]*)" is from "([^"]*)" sector'){ String description, String sector->

    CreateParkingSpaceTestAndAplication.createParkingSpace(description, sector, null, true)
    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.description == description
    assert parkingSpace.sector == sector


}

And(~'^the spot "([^"]*)" is free'){ String description->
    spotDesc = description
    spotState = ParkingSpace.findByDescription(description).owner
    def parkingSpace = ParkingSpace.findByDescription(spotDesc)

    assert parkingSpace.available
}

When(~'^the user asks where to park$'){ ->
    def parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.request.format = "json"
    assert parkingSpaceController.pref(false, true)


}

Then(~'^the system do not change the state of the parking spot$'){ ->
    assert  ParkingSpace.findByDescription(spotDesc).owner == spotState
}

//Given that the user "Pedro" is stored in the system with the password "456" and preference for parking spaces in the "CCEN" sector
//And the user "Pedro" is logged in the system

And(~'^the parking space "([^"]*)" at the sector "([^"]*)" is for "([^"]*)"'){ String description, String sector , String pref ->
    boolean preferential
    CreateParkingSpaceTestAndAplication.createParkingSpace(description, sector, null, true)
    preferential = pref == "elderlies"

//    if (pref == "elderlies") {
//        preferential = true
//    }else{
//        preferential = false
//    }
    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.description == description
    assert parkingSpace.preferential == preferential
}

// And the spot "20" is free

When(~'^the user searches for parking spots for "([^"]*)"$'){ String typePref ->
    boolean preferential

    preferential = typePref == "elderlies"
//    if (typePref == "elderlies") {
//        preferential = true
//    }else{
//        preferential = false
//    }

    def parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.request.format = "json"
    parkingSpaceController.pref(preferential, false)

}

//Then the system do not change the state of the parking spot


//------------------------------------------ GUI ---------------------------------

Given(~/^I am logged with login "([^"]*)" and password "([^"]*)"$/){ String username, String password ->
    to LoginPage
    at LoginPage
    assert page.login(username, password)

}
And(~/^the user is at the home page$/) { ->
    to ParkingSpaceListPage
    at ParkingSpaceListPage

}
When(~/^the user searches for preferential parking spaces$/) { ->
    page.searchParkingSpaces()
}
Then(~/^he is redirected to the view where all the parking spaces are preferential$/) { ->

    waitFor { at ParkingSpaceListPage }
    assert page.verifyPreferential()
}

Given(~/^I am logged with login "([^"]*)" and password "([^"]*)" and have preference for parking spaces at the "([^"]*)" sector$/){ String username, String password, String sector ->
    prefSector = sector
    to LoginPage
    at LoginPage
    assert page.login(username, password)

}
//And the user is at the home page

When(~'^the user searches for parking spaces from his sector$'){ ->
    page.searchParkingSpacesBySector()
}

Then(~/^he is redirected to the view where all the parking spaces are from his sector$/) { ->

    waitFor { at ParkingSpaceListPage }
    assert page.verifySector(prefSector)
}


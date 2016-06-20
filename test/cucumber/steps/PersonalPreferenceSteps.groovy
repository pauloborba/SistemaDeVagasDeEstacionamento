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

def spotDesc
def spotState
//------------------------------------------------ CONTROLADOR --------------------------------------------

Given(~'^the system has stored the user "([^"]*)" with password "([^"]*)" and preference for parking spaces at the "([^"]*)" sector$'){String user, String password, String sector ->

    def currUser = user
    signup(currUser, password, sector)

    assert User.findByUsername(currUser)

}
And(~'^the user "([^"]*)" is logged in the system'){ String user ->
def currUser = user
    login(currUser)
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
    assert parkingSpaceController.filterSpace(false, true)


}

Then(~'^the system do not change the state of the parking spot$'){ ->
    assert  ParkingSpace.findByDescription(spotDesc).owner == spotState
}

//Given that the user "Pedro" is stored in the system with the password "456" and preference for parking spaces in the "CCEN" sector
//And the user "Pedro" is logged in the system

And(~'^the parking space "([^"]*)" at the sector "([^"]*)" is for "([^"]*)"'){ String description, String sector , String pref ->
    boolean preferential
    CreateParkingSpaceTestAndAplication.createParkingSpace(description, sector, null, true)
    if (pref == "elderlies") {
        preferential = true
    }else{
        preferential = false
    }
    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.description == description
    assert parkingSpace.preferential == preferential
}

// And the spot "20" is free

When(~'^the user searches for parking spots for "([^"]*)"$'){ String typePref ->
    boolean preferential

    if (typePref == "elderlies") {
        preferential = true
    }else{
        preferential = false
    }

    def parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.request.format = "json"
    parkingSpaceController.pref(preferential, false)

}

//Then the system do not change the state of the parking spot


//Given the system has stored the user "Pedro" with password "456" and preference for parking spaces in the "CCEN" sector
//And the user "Pedro" is logged in the system

And(~'^the spot "([^"]*)" is not free'){String description ->
    def parkingSpace = ParkingSpace.findByDescription(description)

    def controller = new ParkingSpaceController()
    controller.book(parkingSpace.getId())

    assert !parkingSpace.available

}

//When user asks where to park
//Then the system do not change the state of the parking spot



//------------------------------------------ GUI ---------------------------------

Given(~/^I am logged with login "([^"]*)" and password "([^"]*)"$/){ String username, String password ->
    to LoginPage
    at LoginPage
    assert page.login(username, password)

}
And(~/^the user is at the home page$/) { ->
    to ParkingSpaceListPage
    at ParkingSapceListPage

}
When(~/^the user searches for preferential parking spaces$/) { ->

    pages.searchParkingSpaces()
}
Then(~/^he is redirected to the view where all the parking spaces are preferential$/) { ->

    waitFor { at ParkingSpaceListPage }
    assert pages.verifyPreferential()
}



//------------------------------------------ METODOS -----------------------------
def oldMetaClass

def signup(username, password, sector) {
    def user = new User(username: username, passwordHash: new Sha512Hash(password).toHex(), firstName: "Primeiro nome", lastName: "Último nome", preferredSector: sector)
    user.addToRoles(Role.findByName('User'))
    user.save(flush:true)
}

// Simulação o login do Shiro, uma vez que o Cucumber não nos permite fazer o login através dos controladores
// http://mrdustmite.blogspot.com.br/2010/09/integration-tests-with-shiro-and-nimble.html

def login(username) {
    def metaClassRegistry = GroovySystem.metaClassRegistry

    oldMetaClass = metaClassRegistry.getMetaClass(SecurityUtils)

    metaClassRegistry.removeMetaClass(SecurityUtils)

    def subject = [getPrincipal: { username }, isAuthenticated: { true }] as Subject

    ThreadContext.put(ThreadContext.SECURITY_MANAGER_KEY, [getSubject: { subject } as SecurityManager])

    SecurityUtils.metaClass.static.getSubject = { subject }
}

def logout() {
    GroovySystem.metaClassRegistry.setMetaClass(SecurityUtils, oldMetaClass)
}
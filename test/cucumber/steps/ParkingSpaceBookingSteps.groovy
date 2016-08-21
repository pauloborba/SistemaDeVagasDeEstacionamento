/*
import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash
import pages.LoginPage
import pages.ParkingSpaceListPage
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.ParkingSpace

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def currentUsername
def parkingSpaceDescription
def parkingSpace
def user

Given(~/^the system has the user "([^"]*)" with password "([^"]*)" with "([^"]*)" as prefered sector$/){ String username, String password, String sector ->
    currentUsername = username

    user = new User()
    user.setUsername(username)
    user.setPasswordHash(new Sha512Hash(password).toHex())
    user.setFirstName("Name")
    user.setLastName("Last name")
    user.setPreferredSector(sector)
    user.save(flush: true)

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
    parkingSpace = new ParkingSpace()
    parkingSpace.setDescription(description)
    parkingSpace.setSector("CIn")
    parkingSpace.save(flush: true)

    parkingSpaceDescription = description

    assert ParkingSpace.findByDescription(description).isAvailable()
}

And(~/^the parking space "([^"]*)" isn't available in the system$/){ String description ->
    def otherUser = new User()
    otherUser.setUsername("username")
    otherUser.setFirstName("Outro")
    otherUser.setLastName("Nome")
    otherUser.setPreferredSector("CCEN")
    otherUser.setPasswordHash(new Sha512Hash("123").toHex())
    otherUser.save(flush: true)

    parkingSpace = new ParkingSpace()
    parkingSpace.setDescription(description)
    parkingSpace.setSector("CIn")
    parkingSpace.setOwner(otherUser)
    parkingSpace.save(flush: true)

    assert !parkingSpace.available
}

When(~/^the user tries to book the parking space "([^"]*)"$/){String description ->
    def booked = false

    User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)

    parkingSpace = ParkingSpace.findById(ParkingSpace.findByDescription(description).getId())

    if(parkingSpace.isAvailable()) {
        parkingSpace.setOwner(loggedUser)

        booked = true
    }
    parkingSpace.save(flush: true)

    assert booked
}

When(~/^the user tries to book the unavailable parking space "([^"]*)"$/){String description ->
    def booked = false

    User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)

    parkingSpace = ParkingSpace.findById(ParkingSpace.findByDescription(description).getId())

    if(parkingSpace.isAvailable()) {
        parkingSpace.setOwner(loggedUser)

        booked = true
    }
    parkingSpace.save(flush: true)

    assert !booked
}

Then(~/^the system books the parking space for the user$/){ ->
    assert ParkingSpace.findByDescription(parkingSpaceDescription).getOwner().getUsername() == currentUsername
}

def findByUsername(String username){
    return ParkingSpace.list().find { it.owner && it.owner.username == username };
}

Then(~/^the system doesn't change its state$/){->
    findByUsername((user as User).getUsername()) == null
}

//Scenario: Book parking space web
Given(~/^I am active with login "([^"]*)" and password "([^"]*)"$/){ String username, String password ->
    user = new User()
    user.setUsername(username)
    user.setPasswordHash(new Sha512Hash(password).toHex())
    user.setFirstName("Name")
    user.setLastName("Last name")
    user.setPreferredSector("CCEN")
    user.save(flush: true)

    to LoginPage
    at LoginPage
    assert page.login(username, password)

}

And(~/^The parking space "([^"]*)" in the sector "([^"]*)" is stored$/) { String description, String sector ->
    parkingSpace = new ParkingSpace(description: description, sector: sector, preferential: false, owner: null)
    parkingSpace.save(flush: true)
}

And(~/^I am at the parking space list page$/){ ->
    to ParkingSpaceListPage
    at ParkingSpaceListPage
}

And(~/^I see the parking space "([^"]*)" available in the list$/){ String description ->
    assert page.isAvailable(description)
}

And(~/^Other user books the parking space "([^"]*)" before me$/){ String description ->
    def otherUser = new User()
    def space = ParkingSpace.findByDescription(description)

    otherUser.setFirstName("User")
    otherUser.setLastName("Other")
    otherUser.setPreferredSector("CCEN")
    otherUser.setPasswordHash(new Sha512Hash("123").toHex())
    otherUser.setUsername("nameuser")
    otherUser.save(flush: true)

    space.setOwner(otherUser);

    assert space.save(flush: true)
}


When(~/^I ask to book the parking space "([^"]*)" available$/){ String description ->
    assert page.book(description)
}

Then(~/^I see a message indicating that the parking space was booked with success$/){ ->
    waitFor { at ParkingSpaceListPage }
    assert page.verifyMessage()
}

Then(~/^I see a message indicating that the parking space was not possible book the parking space$/){ ->
    waitFor { at ParkingSpaceListPage }
    assert page.verifyFailBookMessage()
}*/

import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.subject.Subject
import org.apache.shiro.util.ThreadContext
import pages.ParkingSpaceListPage
import sistemadevagasdeestacionamento.ParkingSpaceController
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.Role
import sistemadevagasdeestacionamento.ParkingSpace
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def currentUsername
def parkingSpaceDescription

Given(~/^the system has the user "([^"]*)" with password "([^"]*)" with "([^"]*)" as prefered sector$/){ String username, String password, String sector ->
    currentUsername = username

    signup(username, password, sector)

    assert User.findByUsername(username)
}

And(~/^the user logged in the system$/){ ->
    login(currentUsername)

    def subject = SecurityUtils.subject

    assert subject.principal as String == currentUsername
    assert subject.authenticated
}

And(~/^the parking space "([^"]*)" is available in the system$/){ String description ->
    def controller = new ParkingSpaceController()
    def parkingSpace = new ParkingSpace()


    parkingSpace.setDescription(description)
    controller.saveParkingSpace(parkingSpace)
    parkingSpaceDescription = description


    assert ParkingSpace.findByDescription(description).isAvailable()
}

When(~/^the user tries to book the parking space "([^"]*)"$/){String description ->
    def controller = new ParkingSpaceController()

    assert controller.book(ParkingSpace.findByDescription(description).getId())

}

Then(~/^the system books the parking space for the user$/){ ->
    assert ParkingSpace.findByDescription(parkingSpaceDescription).getOwner().getUsername() == currentUsername
}

//Scenario: Book parking space web
Given(~/^I am active with login "([^"]*)"$/){ String username ->
    login(username)
}

And(~/^I am at the parking space list page$/){ ->
    def parkingSpace = new ParkingSpace()
    parkingSpace.setDescription("CIN-01")
    parkingSpace.save(flush: true)

    at ParkingSpaceListPage
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

// Devolve o estado anterior, removendo o usuário logado
def logout() {
    GroovySystem.metaClassRegistry.setMetaClass(SecurityUtils, oldMetaClass)
}
import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.subject.Subject
import org.apache.shiro.util.ThreadContext
import sistemadevagasdeestacionamento.ParkingSpaceController
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.Role
import sistemadevagasdeestacionamento.ParkingSpace
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

def currentUsername

Given(~/^the system has stored the user "([^"]*)" with password "([^"]*)" and preference for parking spaces in the "([^"]*)" sector$/) { String username, String password, String sector ->
    currentUsername = username

    signup(username, password, sector)

    assert User.findByUsername(username)
}

And(~/^the user is logged in the system$/) { ->
    login(currentUsername)

    def subject = SecurityUtils.subject

    assert subject.principal as String == currentUsername
    assert subject.authenticated
}

And(~/^the parking space "([^"]*)" is from the "([^"]*)" sector $/) { String description, String sector ->
    def controller = new ParkingSpaceController()
    controller.save(new ParkingSpace([description: description, sector: sector]))

    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.description == description
    assert parkingSpace.sector == sector
}

And(~/^the parking space "([^"]*)" is available$/) { String description ->
    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.available
}

def parkingSpaceController

When(~/^the user asks where to park $/) { ->
    parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.request.format = "json"
    parkingSpaceController.suggestion()
}

Then(~/^the systems informs the parking space "([^"]*)" to the user$/) { String description ->
    def response = parkingSpaceController.response.json

    assert response.find { it.description == description }
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
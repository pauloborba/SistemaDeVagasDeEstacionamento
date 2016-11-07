import cucumber.api.PendingException
import pages.*

import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.ParkingSpace
import sistemadevagasdeestacionamento.User
import steps.ParkingSpaceTestDataAndOperations

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/*
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

//#if($ParkingSpaceBooking)
//Controller
Given(~/^O sistema possui o usuario "([^"]*)" cadastrado com preferencia no setor "([^"]*)" "([^"]*)" uso preferencial$/) { String username, String sector, String preferential_tag ->
    def preferential = false

    if (preferential_tag == "com"){
        preferential = true
    }

    AuthHelper.instance.signup(username, sector, preferential)

    def user = User.findByUsername(username)

    assert user.preferential == preferential
    assert user.username == username
    assert user.preferredSector == sector
}

And(~/^O usuario "([^"]*)" esta logado no sistema$/) { String username ->
    AuthHelper.instance.login(username)

    assert AuthHelper.instance.currentUsername == username
}

And(~/^A vaga "([^"]*)" pertence ao setor "([^"]*)" "([^"]*)" uso preferencial$/) { String vaga, String setor, String preferential_tag ->

    def preferential = false

    if (preferential_tag == "com"){
        preferential = true
    }

    ParkingSpaceTestDataAndOperations.createParkingSpace(vaga, setor, preferential)
    ParkingSpace parkingSpace = ParkingSpace.findByDescription(vaga)

    assert parkingSpace?.description == vaga
    assert parkingSpace?.sector == setor
}

And(~/^O usuario logado possui uma reserva na vaga "([^"]*)"$/) { String description ->
    def currentUsername = AuthHelper.instance.currentUsername
    def currentUser = User.findByUsername(currentUsername)

    def currentParkingSpace = ParkingSpace.findByDescription(description)
    ParkingSpaceTestDataAndOperations.bookParkingSpace(currentParkingSpace);

    assert currentParkingSpace.owner == currentUser
}

When(~/^O usuario logado tenta reservar a vaga "([^"]*)"$/) {String description ->

    def currentParkingSpace = ParkingSpace.findByDescription(description)
    assert currentParkingSpace != null

    ParkingSpaceTestDataAndOperations.bookParkingSpace(currentParkingSpace)
}

Then(~/^O sistema altera a reserva de vaga do usuário "([^"]*)" que era "([^"]*)" para a vaga "([^"]*)"$/) { String username, String vaga1, String vaga2 ->
    def currentUser = User.findByUsername(username)
    def pastParkingSpace = ParkingSpace.findByDescription(vaga1)
    def currentParkingSpace = ParkingSpace.findByDescription(vaga2)

    assert pastParkingSpace.isAvailable()
    assert currentParkingSpace.owner == currentUser
    assert !currentParkingSpace.isAvailable()
}

Then(~/^O sistema não permite a reserva da vaga "([^"]*)"$/) { String vaga ->
    def currentUsername = AuthHelper.instance.currentUsername
    def currentUser = User.findByUsername(currentUsername)
    def pastParkingSpace = ParkingSpace.findByDescription(vaga)

    assert pastParkingSpace.owner != currentUser
}


// GUI Scenarios
Given(~/^Eu estou logado no sistema como "([^"]*)" com preferencia no setor "([^"]*)" "([^"]*)" uso preferencial$/) { String username, String sector, String preferential_tag ->
    def preferential = false

    if (preferential_tag == "com"){
        preferential = true
    }

    waitFor { to SignUpPage }
    page.proceed(username, sector, preferential)
    waitFor { at HomePage }
    assert AuthHelper.instance.currentUsername != null
}

And(~/^Eu estou na pagina de home/) { ->
    at HomePage
}

And(~/^Eu vou para a pagina de listagem de vagas$/) { ->
    page.goToParkingSpotListPage()
    at ParkingSpaceListPage
}

And(~/^Eu vou para a pagina de criacao de vaga$/) { ->
    page.goToCreateParkingSpacePage()
    at ParkingSpaceCreatePage
}

And(~/^Eu estou na pagina de listagem de vagas$/) { ->
    at ParkingSpaceListPage
}

And(~/^Eu crio uma vaga com descricao "([^"]*)", no setor "([^"]*)" "([^"]*)" uso preferencial$/) { String descricao, String setor, String preferencial ->
    boolean preferential = false

    if (preferencial == "com"){
        preferential = true
    }
    page.createParkingSpace(descricao, setor, preferential)
    at ParkingSpaceShowPage
}

And(~/^Eu estou na pagina de visualizacao da vaga$/) { ->
    at ParkingSpaceShowPage
}

When(~/^Eu reservo a vaga com descricao "([^"]*)"$/) { String description ->
    def currentUsername = AuthHelper.instance.currentUsername

    at ParkingSpaceListPage
    assert page.bookParkingSpace(description)
    assert page.checkParkingSpace(description, currentUsername)
}


Then(~/^Eu vejo minha vaga ser alterada da vaga "([^"]*)" para a vaga "([^"]*)"$/) { String vaga1, String vaga2 ->
    def currentUsername = AuthHelper.instance.currentUsername

    at ParkingSpaceListPage
    assert !page.checkParkingSpace(vaga1, currentUsername)
    assert page.checkParkingSpace(vaga2, currentUsername)
}

And(~/^Eu tento reservar a vaga com descricao "([^"]*)"$/) { String description ->
    def currentUsername = AuthHelper.instance.currentUsername

    at ParkingSpaceListPage
    assert page.bookParkingSpace(description)
}

Then(~/^Uma mensagem de error aparece na tela$/) { ->
    assert !page.checkSuccessMessage()
}
//#end

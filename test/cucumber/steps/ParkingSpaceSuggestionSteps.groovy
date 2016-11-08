package steps


import pages.*
import sistemadevagasdeestacionamento.*

import javax.validation.constraints.Null

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^the system has stored the user "([^"]*)" with preference for parking spaces in the "([^"]*)" sector$/) { String username, String sector ->
    currentUsername = username

    AuthHelper.instance.signup(username, sector)

    def user = User.findByUsername(username)

    assert user.username == username
    assert user.preferredSector == sector
}

Given(~/^I signed up as "([^"]*)" with preference for parking spaces in the "([^"]*)" sector$/) { String username, String sector ->
    currentUsername = username

    waitFor { to SignUpPage }
    page.proceed(username, sector)
    waitFor { at HomePage }
}

And(~/^the user is logged in the system$/) { ->
    AuthHelper.instance.login(currentUsername)

    assert AuthHelper.instance.currentUsername == currentUsername
}

And(~/^the parking space "([^"]*)" is from the "([^"]*)" sector$/) { String description, String sector ->
    createParkingSpace(description, sector, false)
}

And(~/^the preferential parking space "([^"]*)" is from the "([^"]*)" sector$/) { String description, String sector ->
    createParkingSpace(description, sector, true)
}

And(~/^the parking space "([^"]*)" is available$/) { String description ->
    assert currentParkingSpace.available
}

And(~/^the parking space "([^"]*)" is not available$/) { String description ->
    currentParkingSpace.owner = User.findByUsername(currentUsername)
    currentParkingSpace.save(flush: true)

    assert !currentParkingSpace.available
}

When(~/^I go to parking space's suggestion page$/) { ->
    waitFor { at HomePage }

    page.goToSuggestions()
}

And(~/^I select the filter from parking spaces in my preferred sector$/) { ->
    waitFor { at SuggestionPage }

    page.selectSectorFilter()
}

And(~/^I select the filter from preferential parking spaces$/) { ->
    waitFor { at SuggestionPage }

    page.selectPreferentialFilter()
}

And(~/^I confirm the filter options$/) { ->
    page.confirmFilterOptions()

    waitFor { $("h1[id='lettering']").text() == "Filtradas" }
}

When(~/^the user asks for suggestions of parking spaces$/) { ->
    askForSuggestions(false, false,false)
}

When(~/^the user asks for suggestions of parking spaces on his sector$/) { ->
    askForSuggestions(true, false,false)
}

When(~/^the user asks for suggestions of preferential parking spaces on his sector$/) { ->
    askForSuggestions(true, true,false)
}

Then(~/^the systems informs the parking space "([^"]*)" to the user$/) { String description ->
    shouldInformParkingSpace(description, true)
}

Then(~/^the systems does not inform the parking space "([^"]*)" to the user$/) { String description ->
    shouldInformParkingSpace(description, false)
}

Then(~/^I can see the parking space "([^"]*)" in the suggestions$/) { String description ->
    shouldContainParkingSpace(description, true)
}

Then(~/^I can not see the parking space "([^"]*)" in the suggestions$/) { String description ->
    shouldContainParkingSpace(description, false)
}
//----------------------------- CONTROLLER -----------------------------

Given(~'^o usuário "([^"]*)" está cadastrado no sistema com o setor "([^"]*)"$'){String usuario, String setor ->

    currUsername = usuario

    AuthHelper.instance.signup(usuario, setor)

    def user = User.findByUsername(usuario)

    assert user.username == usuario
    assert user.preferredSector == setor
}

And(~'^o usuário "([^"]*)" está logado no sistema$'){ String usuario ->
    AuthHelper.instance.login(currUsername)

    assert AuthHelper.instance.currentUsername == currUsername
}

And(~'^historico de vagas do usuario "([^"]*)" esta vazio$'){String usuario ->
    UserTestDataAndOperations.createUser(usuario,"pedro" ,"silva" ,"CIn")
    def novoUsuario = User.findByUsername(usuario)
    assert novoUsuario.historicoReservas.empty
}

When(~'^o usuário solicita uma vaga baseada no histórico$'){ ->
    askForSuggestions(false, false, true)
}

Then(~'^o sistema informa as vagas livres ao usuario$'){ ->
   def parkingSpaces = new ParkingSpaceController()
   parkList =  parkingSpaces.suggestion()

}

//Given o usuário "Pedro" está cadastrado no sistema
//And o usuário "Pedro" está logado no sistema


And(~'^as vagas "([^"]*)" e "([^"]*)" estão armazenadas no histórico de vagas do usuário "([^"]*)"$'){ String vaga1, String vaga2, String usuario ->

    UserTestDataAndOperations.createUser(usuario,usuario, "silva","CIn")

    def vag1 = createParkingSpace(vaga1, "CIn", false)
    def vag2 = createParkingSpace(vaga2, "CIn", false)

    def user = User.findByUsername(usuario)

    user.historicoReservas.add(vag1)
    user.historicoReservas.add(vag2)



    assert user.historicoReservas.contains(vag1)
    assert user.historicoReservas.contains(vag2)
}

//When o usuário solicita uma vaga baseada no histórico

Then(~'^o sistema informa a vaga "([^"]*)" esta livre$'){ String vaga->
    def currVaga = ParkingSpace.findByDescription(vaga)
    assert currVaga.available

}

//---------------------------- GUI -------------------------------------

Given(~'^eu estou logado com o login "([^"]*)" com preferencia pelo setor "([^"]*)"$'){ String usuario, String setor ->
    currentUsername = usuario

    to SignUpPage
    page.proceed(usuario, setor)
    at HomePage
}


And(~'^as vagas "([^"]*)" e "([^"]*)" aparecem no histórico de vagas do usuário "([^"]*)"$'){ String vaga1, String vaga2, String usuario ->
    UserTestDataAndOperations.createUser(usuario,usuario, "silva","CIn")

    def vag1 = createParkingSpace(vaga1, "CIn", false)
    def vag2 = createParkingSpace(vaga2, "CIn", false)

    def user = User.findByUsername(usuario)

    user.historicoReservas.add(vag1)
    user.historicoReservas.add(vag2)



    assert user.historicoReservas.contains(vag1)
    assert user.historicoReservas.contains(vag2)
}

And(~'^eu estou na pagina de solicitação de vaga$'){ ->

    at HomePage
    page.goToSuggestions()
    to SuggestionPage
}


Then(~'^uma mensagem de erro aparece$'){ ->

}

Then(~'^sou redirecionado para uma pagina com as vagas "([^"]*)" e "([^"]*)"$'){ String vaga1, String vaga2 ->
    waitFor { at SuggestionPage }
    assert page.containsParkingSpace(vaga1)
    assert page.containsParkingSpace(vaga2)
}

//---------------------------- Metodos -------------------------------------

def createParkingSpace(String description, String sector, boolean preferential) {
    parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.save(new ParkingSpace([description: description, sector: sector, preferential: preferential]))
    parkingSpaceController.response.reset()

    currentParkingSpace = ParkingSpace.findByDescription(description)

    assert currentParkingSpace.description == description
    assert currentParkingSpace.sector == sector
}

def shouldInformParkingSpace(String description, boolean should) {
    def response = parkingSpaceController.response.json

    parkingSpaceController.response.reset()

    def parkingSpace = response.find { it.description == description }


    assert parkingSpace != null == should
}

def askForSuggestions(boolean sector, boolean preferential, boolean historico) {
    def controller = new ParkingSpaceController()
    controller.params << [sector: sector, preferential: preferential, historico: historico]

    controller.request.format = "json"
    controller.suggestion()
}

def shouldContainParkingSpace(String description, boolean should) {
    waitFor { at SuggestionPage }

    assert page.containsParkingSpace(description) == should
}


def shouldInformAll(boolean should){

}
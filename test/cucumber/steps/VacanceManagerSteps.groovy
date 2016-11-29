package steps

import cucumber.api.PendingException
import pages.BookCreatePage
import pages.HomePage
import pages.ParkingSpaceCreatePage
import pages.ParkingSpaceListPage
import pages.ParkingSpaceShowPage
import pages.SignUpPage
import sistemadevagasdeestacionamento.*
import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.Book
import sistemadevagasdeestacionamento.BookController
import sistemadevagasdeestacionamento.ParkingSpace
import sistemadevagasdeestacionamento.ParkingSpaceController

/**
 * Created by gustavo on 06/11/16.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^O sistema possui o usuário "([^"]*)" cadastrado com preferencia no setor "([^"]*)"$/) { String username, String sector->
    // Write code here that turns the phrase above into concrete actions
    AuthHelper.instance.signup(username, sector)

    def user = User.findByUsername(username)

    assert user.username == username
    assert user.preferredSector == sector
}

And(~/^O usuário "([^"]*)" está logado no sistema$/) { String username ->
    AuthHelper.instance.login(username)
    assert AuthHelper.instance.currentUsername == username
}

And(~/^As vagas "([^"]*)" e "([^"]*)" dos setores "([^"]*)" e "([^"]*)" estão livres$/) {
    String desc1, String desc2, String sector1, String sector2->

    ParkingSpaceTestDataAndOperations.createParkingSpace(desc1, sector1, false)
    ParkingSpaceTestDataAndOperations.createParkingSpace(desc2, sector2, false)

    def vaga1 = ParkingSpace.findByDescription(desc1)
    def vaga2 = ParkingSpace.findByDescription(desc2)

    assert vaga1.isAvailable() && vaga2.isAvailable()
}

When(~/^"([^"]*)" cria uma reserva da vaga "([^"]*)" para o horário das "([^"]*)" às "([^"]*)" horas do dia corrente$/) { String username, String desc, Integer entrada, Integer saida ->
    ParkingSpaceTestDataAndOperations.createBook(username, desc, entrada, saida)
//    controller.create(params: [parkingSpace: desc, inHour: entrada, outHour: saida])
}
Then(~/^O Sistema reserva a vaga "([^"]*)" para "([^"]*)"$/) { String desc, String username ->
    def vaga = ParkingSpace.findByDescription(desc)
    assert vaga.owner.getUsername() == username
}
And(~/^A reserva da vaga "([^"]*)" recebe o valor "([^"]*)" para Hora de entrada$/) { String desc, Integer entrada ->
    ParkingSpace vaga = ParkingSpace.findByDescription(desc)
    def reserva = Book.findByParkingSpace(vaga)
    assert reserva.getInHour() == entrada
}
And(~/^A reserva da vaga "([^"]*)" recebe o valor "([^"]*)" para Hora de saída$/) { String desc, Integer saida ->
    def vaga = ParkingSpace.findByDescription(desc)
    def reserva = Book.findByParkingSpace(vaga)

    assert reserva.getOutHour() == saida
}

And(~/^"([^"]*)" reservou a vaga "([^"]*)" das "([^"]*)" às "([^"]*)" horas do dia corrente$/) { String username, String desc, Integer entrada, Integer saida ->
    ParkingSpaceTestDataAndOperations.createBook(username, desc, entrada, saida)
    def vaga = ParkingSpace.findByDescription(desc)
    assert vaga.owner.getUsername() == username
}
When(~/^o horário do sistema passar das "([^"]*)" horas$/) { Integer saida ->
    def currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    assert currentHour > saida
}
And(~/^"([^"]*)" ainda está na vaga "([^"]*)"$/) { String username, String desc ->
    def vaga = ParkingSpace.findByDescription(desc)
    assert vaga.getOwner().getUsername() == username
}

Then(~/^A reserva da vaga "([^"]*)" recebe o status "([^"]*)"$/) { String desc, String status ->
    def vaga = ParkingSpace.findByDescription(desc)
    ParkingSpaceTestDataAndOperations.checkBooksTimes()
    assert vaga.getBook().getStatus() == status
}

Given(~/^Eu estou logado no sistema como "([^"]*)" com preferência no setor "([^"]*)"$/) { String username, String sector->
    waitFor { to SignUpPage }
    page.proceed(username, sector)
    waitFor { at HomePage }
    assert AuthHelper.instance.currentUsername == username
}
And(~/^Eu criei uma vaga com com descricao "([^"]*)", no setor "([^"]*)"$/) { String desc, String sector ->
    at HomePage
    page.goToParkingSpotListPage()
    at ParkingSpaceListPage
    page.goToCreateParkingSpace()
    at ParkingSpaceCreatePage
    page.createParkingSpace(desc, sector)
    at ParkingSpaceShowPage
}

When(~/^Eu seleciono a opção Reservar da vaga com descrição "([^"]*)"$/) { String desc ->
    page.goToBookParkingSpace(desc)
    at BookCreatePage
}
And(~/^Eu crio uma reserva para a vaga para o horário de "([^"]*)" às "([^"]*)" horas do dia corrente$/) {
    Integer entrada, Integer saida ->
    page.createBook(entrada, saida)
}

Then(~/^Eu estou na página da listagem de vagas$/) { ->
    page.goToParkingSpotListPage()
    at ParkingSpaceListPage
}
And(~/^Eu vejo "([^"]*)" na coluna Book da vaga com descrição "([^"]*)"$/) { String tempoDeReservaEsperado, String desc ->
    String tempoDeReserva = page.getBookTime(desc)
    assert tempoDeReserva.equals(tempoDeReservaEsperado)
}
And(~/^o horário do sistema passou das "([^"]*)" horas$/) { Integer saida ->
    def currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    assert currentHour > saida
}
And(~/^Eu vejo o texto da coluna Book da vaga com descrição "([^"]*)" na cor "([^"]*)"$/) { String desc, String color ->
    def rgbColor = ParkingSpaceTestDataAndOperations.getRgbColorString(color)
    def statusColor = page.getBookStatusColor(desc)
    assert statusColor == rgbColor
}
package steps

import cucumber.api.PendingException
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
    ParkingSpaceTestDataAndOperations.createBook(desc, entrada, saida)
//    controller.create(params: [parkingSpace: desc, inHour: entrada, outHour: saida])
}
Then(~/^O Sistema reserva a vaga "([^"]*)" para "([^"]*)"$/) { String desc, String username ->
    def vaga = ParkingSpace.findByDescription(desc)
    assert vaga.owner == username
}
And(~/^A reserva da vaga "([^"]*)" recebe o valor "([^"]*)" para Hora de entrada$/) { String desc, Integer entrada ->
    def vaga = ParkingSpace.findByDescription(desc)
    def reserva = Book.findByParkingSpace(vaga)
    assert reserva.inHour == entrada
}
And(~/^A reserva da vaga "([^"]*)" recebe o valor "([^"]*)" para Hora de saída$/) { String desc, Integer saida ->
    def vaga = ParkingSpace.findByDescription(desc)
    def reserva = Book.findByParkingSpace(vaga)

    assert reserva.outHour == saida
}

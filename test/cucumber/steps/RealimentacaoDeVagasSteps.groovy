package steps

import pages.GestorVagaPage
import pages.RealimentacaoDeVagas


Given(~'A vaga "([^"]*)" está ocupada $'){int numSpot ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 1

}



When(~'O gestor de vagas remove a reserva da vaga "([^"]*)" $'){int numSpot ->
    assert withConfirm(true) {$("input", name: "_action_remove").click() } == "Remover Reserva"

}



Then(~'O sistema deixa a vaga "([^"]*)" desocupada $'){int numSpot ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 0
}

Given(~'A vaga "([^"]*)" está livre $'){int numSpot ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 0

}



When(~'O gestor de vagas reserva a vaga "([^"]*)" $'){int numSpot ->
    page.reserveSpot(numSpot)

}



Then(~'O sistema reserva a vaga "2"$'){int numSpot ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 1
}



//----------------------------------------- GUI TESTS -------------------------------------------

Given(~'Eu estou logado como gestor de vagas $'){ ->
    to GestorVagaPage
    at GestorVagaPage
}


And(~'A vaga "([^"]*)" está ocupada '){int numSpot  ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 1

}


When(~'O gestor de vagas solicita a remoção da reserva da vaga "([^"]*)" $'){ int numSpot->
    page.removeSpotReserve(numSpot)

}



Then(~'O sistema informa a vaga "([^"]*)" como desocupada para "([^"]*)" $'){int numSpot, String gestVagas ->
    v = vaga.findByNum(spot)
    assert vaga.status == 0

}

Given(~'Eu estou logado como gestor de vagas $'){ ->
    to GestorVagaPage
    at GestorVagaPage
}


And(~'A vaga "([^"]*)" está livre '){int numSpot  ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 0

}


When(~'O gestor de vagas solicita a reserva da vaga "([^"]*)" $'){ int numSpot->
    page.reserveSpot(numSpot)

}



Then(~'O sistema informa a vaga "([^"]*)" como desocupada para "([^"]*)" $'){int numSpot, String gestVagas ->
    v = vaga.findByNum(spot)
    assert vaga.status == 0

}
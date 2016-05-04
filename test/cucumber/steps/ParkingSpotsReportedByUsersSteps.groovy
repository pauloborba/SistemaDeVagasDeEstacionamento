package steps

import org.spockframework.compiler.model.ThenBlock

//1
Given(~'that the system indicated to the user "([^"]*)" the parking spot "([^"]*)" $'){ String user, int numSpot->
        user = user.findByName(user)
        assert numSpot == user.vaga

}


When(~' the user "([^"]*)" fills in the system that he parked in the parking spot "([^"]*)" $'){ String user, int numSpot ->
    page.fillUserSpot(user,numSpot)

}


Then(~'the system changes the state of the parking spot "([^"]*)" $'){ int numSpot->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 1

}


//2

Given(~'that the system indicated to the user "([^"]*)" the parking spot "([^"]*)" $'){ String user, int numSpot->
    user = user.findByName(user)
    assert numSpot == user.vaga

}

And(~'the parking spot "([^"]*)" is free '){int numSpot  ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 0

}

When(~' the user "([^"]*)" fills in the system that he parked in the parking spot "([^"]*)" $'){ String user, int numSpot ->
    page.fillUserSpot(user,numSpot)

}


Then(~'the system do not changes the state of the parking spot "([^"]*)" $'){ int numSpot ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == vaga.status
}


//----------------------------------------- GUI TESTS -------------------------------------------

//1
Given(~'that the system indicated to the user "([^"]*)" the parking spot "([^"]*)"$'){ String user ->
    user = user.findByName(user)
    assert numSpot == user.vaga

}



When(~'the user "([^"]*)" fills in the system that parked in the parking spot "([^"]*)"$'){ String user, int numSpot ->

    page.fillUserSpot(user,numSpot)

}

And(~'the parking spot "([^"]*)" is free'){ int numSpot ->
   vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 0

}

Then(~'the system informs that the state of the parking spot "([^"]*)" was changed with success$'){ int numSpot ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 1
    to spotReservedPage
}

//2

Given(~'that the system indicated to the user "([^"]*)" the parking spot "([^"]*)"$'){ String user ->
    user = user.findByName(user)
    assert numSpot == user.vaga

}



When(~'the user "([^"]*)" fills in the system that parked in the parking spot "([^"]*)"$'){ String user, int numSpot ->

    page.fillUserSpot(user,numSpot)

}

And(~'the parking spot "([^"]*)" is free'){ int numSpot ->
    vaga = vaga.findByNumber(numSpot)
    assert vaga.status == 0

}

Then(~'appears a menu for the user "([^"]*)" to select which parking spot his car is parked$'){
    to selectParkingSpotPage

}
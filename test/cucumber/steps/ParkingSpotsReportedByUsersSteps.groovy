package steps

import static cucumber.api.groovy.EN.*


//1

 Given(~'^that the system indicated to the user "([^"]*)" the parking spot "([^"]*)"$'){ String user, int numSpot->
//      user = user.findByName(user)
  //      assert numSpot == user.vaga

}


When(~'^the user "([^"]*)" fills in the system that he parked in the parking spot "([^"]*)" $'){ String user, int numSpot ->
    //page.fillUserSpot(user,numSpot)

}


Then(~'^the system changes the state of the parking spot "([^"]*)" $'){ int numSpot->
    //vaga = vaga.findByNumber(numSpot)
    //assert vaga.status == 1

}


And(~'^the parking spot "([^"]*)" is free '){int numSpot  ->
 //   vaga = vaga.findByNumber(numSpot)
   // assert vaga.status == 0

}

Then(~'^the system informs that the state of the parking spot "([^"]*)" was changed with success$'){ String user ->
    // to selectParkingSpotPage

}

Then(~'^the system do not changes the state of the parking spot "([^"]*)" $'){ int numSpot ->
    /*vaga = vaga.findByNumber(numSpot)
      assert vaga.status == vaga.status */
}

Then(~'^appears a menu for the user "([^"]*)" to select which parking spot his car is parked$'){ String user ->
    // to selectParkingSpotPage

}
//----------------------------------------- GUI TESTS -------------------------------------------

//1

And(~'^the parking spot "([^"]*)" is free'){ int numSpot ->
    /* vaga = vaga.findByNumber(numSpot)
        assert vaga.status == 0 */

}

Then(~'^the system changes the state of the parking spot "([^"]*)"$'){ int numSpot ->
    //vaga = vaga.findByNumber(numSpot)
    //assert vaga.status == 1
    //to spotReservedPage
}



When(~'^the user "([^"]*)" fills in the system that he parked in the parking spot "([^"]*)"$'){ String user, int numSpot ->

  //  page.fillUserSpot(user,numSpot)

}


Then(~'^the system do not changes the state of the parking spot "([^"]*)"$'){ String user ->
    // to selectParkingSpotPage

}


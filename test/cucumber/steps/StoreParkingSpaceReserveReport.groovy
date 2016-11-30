package steps

import pages.*
import sistemadevagasdeestacionamento.*



this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
//#if($Parking Space's reserve report)
Given(~/^The system has stored the user "([^"]*)" with preference for parking spaces in the "([^"]*)" sector$/) { String username, String sector ->

    AuthHelper.instance.signup(username, sector)

    def user = User.findByUsername(username)

    assert user.username == username
    assert user.preferredSector == sector
}

And(~/^the user "([^"]*)" is logged in the system$/) { String currentUsername ->
    AuthHelper.instance.login(currentUsername)
    assert AuthHelper.instance.currentUsername == currentUsername
}

And(~/^The Parking Space "([^"]*)" is available$/) { String desc ->
    ParkingSpaceTestAndOperations.createParkingSpace(desc, "CIn", false)
    def _PS = ParkingSpace.findByDescription(desc)

    assert _PS.isAvailable()
}

And(~/^The Parking Space "([^"]*)" was booked$/) { String desc1 ->
    ParkingSpaceTestAndOperations.createParkingSpace(desc1, "CCEN", false)
    def pS = ParkingSpace.findByDescription(desc1)

    ParkingSpaceTestAndOperations.bookParkingSpace(pS)

    assert pS.isAvailable()
    assert ParkingSpace.findByDescription(desc1) == pS
}

When(~/^the user can not book the Parking Space "([^"]*)"$/) { String desc ->
    def currentPS = ParkingSpace.findByDescription(desc)
    assert currentPS.getOwner() != null
}

When(~/^the user tries book the parking space "([^"]*)"$/) { String desc->
    def currentPS = ParkingSpace.findByDescription(desc)
    ParkingSpaceTestAndOperations.bookParkingSpace(currentPS)

    assert ParkingSpace.findByDescription(desc) == currentPS
}

Then(~/^The system should have stored the parking space's reserve report from "([^"]*)"$/) { String user ->
    def currUser = User.findByUsername(user)
    def _PSR = ReservedParkingSpace.findAllByUser(currUser)

    assert _PSR != null
}

Then(~/^The system should not have stored the parking space's reserve report from "([^"]*)"$/) { String user ->
    def currUser = User.findByUsername(user)
    def _PSR = ReservedParkingSpace.findAllByUser(currUser)

    assert _PSR == null
}

// GUI


Given(~/^I signed up as "Junior" with preference for parking spaces in the "CIn" sector "([^"]*)"$/) { String username, String sector ->
    waitFor { to SignUpPage }
    page.proceed(username, sector)
    assert AuthHelper.instance.currentUsername != null
    waitFor { at HomePage }
}

And(~/^I am at the Parking spot list page$/) { ->
    to ParkingSpaceList
    at ParkingSpaceList
}

And(~/^I created a Parking Space with description "([^"]*)", in the sector "([^"]*)"$/) { String descricao, String setor ->
    at ParkingSpaceList
    page.goToCreateParkingSpacePage()
    at ParkingSpaceCreatePage
    page.createParkingSpace(descricao, setor)
    at ParkingSpaceShowPage
    page.goToParkingSpotListPage()
}

And(~/^I don't have any parking space reserved$/) { ->
    at ParkingSpaceList
    assert !page.checkParkingSpace("CIn-03", AuthHelper.instance.currentUsername )
}

When(~/^I try to see my reserved Parking Space Reports$/) { ->
    to HomePage
    at HomePage
}

Then(~/^I should see an error message$/) { ->
    assert page.hasErrors()
}
//#end
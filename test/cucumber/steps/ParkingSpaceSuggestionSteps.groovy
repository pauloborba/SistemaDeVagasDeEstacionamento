package steps

import sistemadevagasdeestacionamento.*

import static cucumber.api.groovy.EN.*

Given(~/^the system stored that the user "([^"]*)" has preference for parking spaces in the "([^"]*)" sector$/) { String login, String sector ->
    def userController = new UserController()
    userController.params << [login: login, preferredSector: sector]
    userController.save
    userController.response.reset

    def user = User.findByLogin(login)

    assert user
    assert user.preferredSector == sector
}

And(~/^the parking space "([^"]*)" is from the "([^"]*)" sector$/) { String parkingSpace, String sector ->
    assert true
}

And(~/^the parking space "([^"]*)" is available$/) { String parkingSpace ->
    assert true
}

When(~/^the user "([^"]*)" asks where to park$/) { String user ->
    assert true
}

Then(~/^the systems informs the parking space "([^"]*)" to the user$/) { String parkingSpace ->
    assert true
}
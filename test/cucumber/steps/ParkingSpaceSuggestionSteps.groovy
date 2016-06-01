package steps

import sistemadevagasdeestacionamento.*

import static cucumber.api.groovy.EN.*

Given(~/^the user "([^"]*)" named "([^"]*)" "([^"]*)" has preference for parking spaces in the "([^"]*)" sector$/) { String login, String firstName, String lastName, String preferredSector ->
    def userController = new UserController()
    userController.params << [login: login, firstName: firstName, lastName: lastName, preferredSector: preferredSector]
    userController.cucumberSave()
    userController.response.reset()

    def user = User.findByLogin(login)

    assert user.firstName == firstName
    assert user.lastName == lastName
    assert user.preferredSector == preferredSector
}

And(~/^the parking space "([^"]*)" is from the "([^"]*)" sector$/) { String description, String sector ->
    def parkingSpaceController = new ParkingSpaceController()
    parkingSpaceController.params << [description: description, sector: sector]
    parkingSpaceController.cucumberSave()
    parkingSpaceController.response.reset()

    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.description == description
    assert parkingSpace.sector == sector
}

And(~/^the parking space "([^"]*)" is available$/) { String description ->
    def parkingSpace = ParkingSpace.findByDescription(description)

    assert parkingSpace.available
}

When(~/^the user "([^"]*)" asks where to park$/) { String user ->
    assert true
}

Then(~/^the systems informs the parking space "([^"]*)" to the user$/) { String parkingSpace ->
    assert true
}
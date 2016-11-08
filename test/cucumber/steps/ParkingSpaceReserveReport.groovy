package steps

import pages.*
import sistemadevagasdeestacionamento.*



this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^The system has stored the user "([^"]*)" with preference for parking spaces in the "([^"]*)" sector$/) { String username, String sector ->

    AuthHelper.instance.signup(username, sector)

    def user = User.findByUsername(username)

    assert user.username == username
    assert user.preferredSector == sector
}

And(~/^the user "([^"]*)" is logged in the system/) { String currentUsername ->
    AuthHelper.instance.login(currentUsername)

    assert AuthHelper.instance.currentUsername == currentUsername
}

And(~/^There is parking space's reserve record for the user "([^"]*)"/) { String currentUser ->
    def user = User.findByUsername(currentUser)
    assert user.available
}

And(~/^There is not parking space's reserve record for the user "([^"]*)"/) { String currentUser ->
    def user = User.findByUsername(currentUser)
    assert !user.available
}

When(~/^the user tries to emit the parking space's reserve report/) { ->
    waitFor { at HomePage }
}

Then(~/^The system shows the parking space's reserve report from "([^"]*)"$/) { String user ->
   def currUser = User.findByUsername(user)
   ReservedParkingSpaceController.index()
}

Then(~/^the system don't emit the parking space's reserve report/) {  ->

}
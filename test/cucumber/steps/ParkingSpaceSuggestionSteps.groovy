import cucumber.api.PendingException

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^the system stored that the user "([^"]*)" has preference for parking spaces in the "([^"]*)" sector$/) { String user, String sector ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

And(~/^the parking space "([^"]*)" is from the "([^"]*)" sector$/) { String spot, String sector ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

And(~/^the parking space "([^"]*)" is available$/) { String spot ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

When(~/^the user "([^"]*)" asks where to park$/) { String user ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

Then(~/^the systems informs the parking space "([^"]*)" to the user$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^the system stored that the user "([^"]*)" has preference for parking spaces in the "([^"]*)" sector$/) { String user, String sector ->
    assert true
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
package steps

import cucumber.api.PendingException
import pages.*
import sistemadevagasdeestacionamento.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

// extract method
def static createProblemReport(User owner, String title, String sector, String description) {
    def problemReport = new ProblemReport([user: owner, title: title, sector: sector, description: description])
    ProblemReportController controller = new ProblemReportController()
    controller.save(problemReport)
    controller.response.reset()
}

def shouldContainProblemReport(String title, boolean should) {
    at ProblemReportPage

    assert page.containsProblemReport(title) == should
}

// Controller
Given(~/^The system has stored the user "([^"]*)" with preference parking spaces in the "([^"]*)" sector$/) { String username, String sector ->
    currentUsername = username
    AuthHelper.instance.signup(username, sector,false)
    def user = User.findByUsername(username)
    assert user.username == username
    assert user.preferredSector == sector
}

And(~/^The problem report list has the problem with title "([^"]*)", sector "([^"]*)" and description "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    def username = AuthHelper.instance.currentUsername
    def user = User.findByUsername(username)
    createProblemReport(user, arg1, arg2, arg3)
    def problemReport = ProblemReport.findByTitle(arg1)
    assert problemReport != null
    assert problemReport.title == arg1
    assert problemReport.sector == arg2
    assert problemReport.description == arg3
}

And(~/^The user is logged in the system$/) { ->
    AuthHelper.instance.login(currentUsername)
    assert AuthHelper.instance.currentUsername == currentUsername
}

When(~/^The user try to set as solved the problem "([^"]*)"$/) { String arg1 ->
    def problemReport = ProblemReport.findByTitle(arg1)
    ProblemReportController controller = new ProblemReportController()
    assert controller.resolve(problemReport)
}

Then(~/^The problem "([^"]*)" is not modified$/) { String arg1 ->
    def problemReport = ProblemReport.findByTitle(arg1)
    assert problemReport != null
}

Then(~/^The problem "([^"]*)" is removed from parking report list$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    def problemReport = ProblemReport.findByTitle(arg1)
    assert problemReport == null
}

// Gui
Given(~/^I signed up as "([^"]*)" with preference parking spaces in the "([^"]*)" sector$/) { String username, String sector ->
    waitFor { to SignUpPage }
    page.proceed(username, sector)
    waitFor { at HomePage }
    assert AuthHelper.instance.currentUsername == username
}

When(~/^I go to parking report list page$/) { ->
    waitFor { at HomePage }

    page.goToProblemReport()
}

And(~/^I see problem "([^"]*)" in parkin report list$/) { String title ->
    shouldContainProblemReport(title, true)
}

And(~/^I select the option to set the problem "([^"]*)" as solved$/) { String title ->
    assert page.resolve(title)
}

Then(~/^I can not see the problem "([^"]*)" in the parking problem list$/) { String title ->
    shouldContainProblemReport(title, false)
}
Then(~/^I see the problem "([^"]*)" continues in the parking report list$/) { String title ->
    shouldContainProblemReport(title, true)
}
package steps

import cucumber.api.PendingException
import pages.*
import sistemadevagasdeestacionamento.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)




def static createProblemReport(User owner, String title, String sector, String description) {
    def problemReport = new ProblemReport([user: owner, title: title, sector: sector, description: description])
    ProblemReportController controller = new ProblemReportController()
    controller.save(problemReport)
    controller.response.reset()
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

And(~/^The user "([^"]*)" has sended a report with the title "([^"]*)", sector "([^"]*)", and description "([^"]*)"$/) { String arg1, String arg2, String arg3, String arg4 ->
    def user = User.findByUsername(arg1)
    createProblemReport(user, arg2, arg3, arg4)
    def problemReport = ProblemReport.findByTitle(arg2)
    assert problemReport != null
    assert problemReport.user == user
    assert problemReport.title == arg2
    assert problemReport.sector == arg3
    assert problemReport.description == arg4

}

And(~/^The problem "([^"]*)" is not modified$/) { String arg1 ->
    def problemReport = ProblemReport.findByTitle(arg1)
    assert problemReport != null
}
Then(~/^The problem "([^"]*)" is removed from parking report list$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    def problemReport = ProblemReport.findByTitle(arg1)
    assert problemReport == null
}
Given(~/^The system has stored the user "([^"]*)" with preference for parking spaces in the "([^"]*)" sector$/) { String username, String sector ->
    currentUsername = username

    AuthHelper.instance.signup(username, sector)

    def user = User.findByUsername(username)

    assert user.username == username
    assert user.preferredSector == sector
}
And(~/^The user is logged in the system$/) { ->
    AuthHelper.instance.login(currentUsername)

    assert AuthHelper.instance.currentUsername == currentUsername
}
Given(~/^I signed up as "([^"]*)" with preference for parking spaces in the "([^"]*)" sector$/) { String username, String sector ->
    currentUsername = username

    waitFor { to SignUpPage }
    page.proceed(username, sector)
    waitFor { at HomePage }
}
When(~/^The user try to set as solved the problem "([^"]*)"$/) { String arg1 ->
    def problemReport = ProblemReport.findByTitle(arg1)
    ProblemReportController controller = new ProblemReportController()
    assert controller.resolve(problemReport)


}
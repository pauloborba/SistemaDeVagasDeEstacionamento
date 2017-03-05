package steps

import cucumber.api.PendingException
import org.apache.tools.ant.taskdefs.WaitFor
import pages.*
import sistemadevagasdeestacionamento.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

// Controller
Given(~/^The system has stored the user "([^"]*)" with preference parking spaces in the "([^"]*)" sector$/) { String username, String sector ->
    currentUsername = username
    AuthHelper.instance.signup(username, sector,false)
    def user = User.findByUsername(username)
    assert user.username == username
    assert user.preferredSector == sector
}

And(~/^The problem report list has the problem with title "([^"]*)", sector "([^"]*)" and description "([^"]*)"$/) { String arg1, String arg2, String arg3 ->

    ProblemReportTestDataAndOperations.createProblemReport(arg1, arg2, arg3)
    def problemReport = ProblemReport.findByTitle(arg1)
    assert problemReport != null
    assert problemReport.title == arg1
    assert problemReport.sector == arg2
    assert problemReport.description == arg3
}

And(~/^The user logged in the system as "([^"]*)"$/) { String username->
    AuthHelper.instance.login(username)
    assert AuthHelper.instance.currentUsername == username

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
    waitFor { at ProblemReportShowPage }
    page.goToProblemReportListPage()

}

And(~/^I see problem "([^"]*)" in parkin report list$/) { String title ->
    waitFor{ at ProblemReportPage}

    assert page.containsProblemReport(title) == true
}

And(~/^I select the option to set the problem "([^"]*)" as solved$/) { String title ->
    assert page.resolve(title)
}

Then(~/^I can not see the problem "([^"]*)" in the parking problem list$/) { String title ->
    waitFor{ at ProblemReportPage }
    assert page.containsProblemReport(title) == false
}
Then(~/^I see the problem "([^"]*)" continues in the parking report list$/) { String title ->
    waitFor{ at ProblemReportPage}

    assert page.containsProblemReport(title) == true
}
And(~/^I sent a problem with title "([^"]*)", sector "([^"]*)" and description "([^"]*)"$/) { String title, String sector, String description ->
    waitFor { to CreateProblemReportPage }
    waitFor { at CreateProblemReportPage }
    page.fillProblemReportInformations(title, sector, description)
    page.selectCreateProblemReport()
}
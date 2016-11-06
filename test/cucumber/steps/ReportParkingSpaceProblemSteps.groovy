/**
 * Created by João Pedro on 03/10/2016.
 */
package steps

import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.ProblemReport
import pages.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//controller
Given(~/^the system has stored the user "([^"]*)" with "([^"]*)" as preferred sector$/) { String username, String sector ->
    AuthHelper.instance.signup(username, sector)
    def user = User.findByUsername(username)
    assert user.username == username
    assert user.preferredSector == sector
}

And(~/^the user is logged in the system as "([^"]*)"$/) { String username->
    AuthHelper.instance.login(username)
    assert AuthHelper.instance.currentUsername == username
}

When(~/the user tries to send the report with Title "([^"]*)", Sector "([^"]*)" and Description "([^"]*)"$/) { String title, String sector, String description ->
    def username = AuthHelper.instance.currentUsername
    def user = User.findByUsername(username)
    ProblemReportTestDataAndOperations.createProblemReport(user, title, sector, description)
    def currentProblemReport = ProblemReport.findByTitle(title)

    assert currentProblemReport != null

}

Then(~/the system stores the report "([^"]*)"$/){ String title ->
    def problemReport = ProblemReport.findByTitle(title)
    assert problemReport != null
}

Then(~/the system doesn't store the report "([^"]*)" with description "([^"]*)"$/) { String title, String description ->
    def problemReport = ProblemReport.findByTitle(title)
    assert problemReport.description != description
}

And(~/^The system has no problem report stored with Title "([^"]*)"$/) { String title ->
    def problemReport = ProblemReport.findByTitle(title)
    assert problemReport == null
}

And(~/^The system has a problem report stored with Title "([^"]*)"$/) { String title ->

    def username = AuthHelper.instance.currentUsername
    def user = User.findByUsername(username)
    ProblemReportTestDataAndOperations.createProblemReport(user, title,"CIn","Acidente nas vagas c3 e c4")
    def problemReport = ProblemReport.findByTitle(title)
    assert problemReport != null
}
//gui

Given(~/^I signed up in the system as "([^"]*)" with "([^"]*)" as preferred sector$/) { String username, String sector ->
    waitFor { to SignUpPage }
    page.proceed(username, sector)
    assert AuthHelper.instance.currentUsername != null
    waitFor { at HomePage }
}

When(~/^I go to report parking space problem page$/){ ->

    page.goToCreateProblemReport()
    waitFor { at CreateProblemReportPage }
}

And(~/^I fill the report informations with Title "([^"]*)", Sector "([^"]*)" and Description "([^"]*)"$/) { String title, String sector, String description ->
    waitFor { at CreateProblemReportPage }
    page.fillProblemReportInformations(title,sector,description)
}

And(~/^I send the problem report$/){ ->

    waitFor { at CreateProblemReportPage }
    page.selectCreateProblemReport()
}

Then(~/^I shoud see a message indicating that the report was created$/){ ->
    assert page.readFlashMessage() != null
}

And(~/^I try to send a different problem report with title "([^"]*)"$/){ String title ->
    waitFor { to CreateProblemReportPage }
    waitFor { at CreateProblemReportPage }
    page.fillProblemReportInformations(title,"Cin","description")
    page.selectCreateProblemReport()
}

Then(~/^I shoud see an error message$/){ ->
    assert page.hasErrors()
}


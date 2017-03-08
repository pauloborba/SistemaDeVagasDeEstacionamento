//#if($ReportParkingSpaceProblem)
package steps

import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.ProblemReport
import pages.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//controller
Given(~/^the system has stored the user "([^"]*)" with "([^"]*)" as preferred sector$/) { String username, String sector ->
    AuthHelper.instance.signup(username, sector, false)
    def user = User.findByUsername(username)
    assert user.username == username
    assert user.preferredSector == sector
}

And(~/^the user is logged in the system as "([^"]*)"$/) { String username->
    AuthHelper.instance.login(username)
    assert AuthHelper.instance.currentUsername == username
}

When(~/^the user tries to send the report with Title "([^"]*)", Sector "([^"]*)" and Description "([^"]*)"$/) { String title, String sector, String description ->

    ProblemReportTestDataAndOperations.createProblemReport(title, sector, description)
    def currentProblemReport = ProblemReport.findByTitle(title)
    assert currentProblemReport != null

}
When(~/^the user tries to send the report "([^"]*)" with some information left incomplete$/) { String title->
    ProblemReportTestDataAndOperations.createProblemReport(title, "CCEN", "")

}

Then(~/^the system stores the report "([^"]*)"$/){ String title ->
    def problemReport = ProblemReport.findByTitle(title)
    assert problemReport != null
}

Then(~/^the system doesn't store the report "([^"]*)" with description "([^"]*)"$/) { String title, String description ->
    def problemReport = ProblemReport.findByTitle(title)
    assert problemReport.description != description
}

Then(~/^the system doesn't store the report "([^"]*)"$/) { String title ->

    assert ProblemReport.findByTitle(title) == null
}

And(~/^The system has no problem report stored with Title "([^"]*)"$/) { String title ->
    def problemReport = ProblemReport.findByTitle(title)
    assert problemReport == null
}

And(~/^The system has a problem report stored with Title "([^"]*)"$/) { String title ->

    ProblemReportTestDataAndOperations.createProblemReport(title,"CIn","Acidente nas vagas c3 e c4")
    def problemReport = ProblemReport.findByTitle(title)
    assert problemReport != null
}
//gui

Given(~/^I signed up in the system as "([^"]*)" with "([^"]*)" as preferred sector$/) { String username, String sector ->
    waitFor { to SignUpPage }
    page.proceed(username, sector, false)
    assert AuthHelper.instance.currentUsername == username
    waitFor { at HomePage }
}

And(~/^I am at report parking space problem page$/){ ->
    waitFor { to CreateProblemReportPage }
    waitFor { at CreateProblemReportPage }

}

And(~/^I sent a report with Title "([^"]*)"$/){ title ->
    waitFor { to CreateProblemReportPage }
    waitFor { at CreateProblemReportPage }
    page.fillProblemReportInformations(title,"CIn","description")
    page.selectCreateProblemReport()
}

When(~/^I try to send a report with Title "([^"]*)", Sector "([^"]*)" and Description "([^"]*)"$/){ String title, String sector, String description ->
    waitFor { at CreateProblemReportPage }
    page.fillProblemReportInformations(title,sector,description)
    page.selectCreateProblemReport()
}

When(~/^I try to send a report with some information left incomplete$/){ ->
    waitFor { at CreateProblemReportPage }
    page.fillProblemReportInformations("titulo","CCEN","")
    page.selectCreateProblemReport()
}

Then(~/^I should see a message indicating that the report was created$/){ ->
    assert page.readFlashMessage() != null
}

Then(~/^I should see an error message$/){ ->
    assert page.hasErrors()
}


//#end

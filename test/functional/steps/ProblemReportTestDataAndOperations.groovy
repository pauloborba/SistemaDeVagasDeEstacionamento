//#if($ReportParkingSpaceProblem)
package steps

import sistemadevagasdeestacionamento.ProblemReport
import sistemadevagasdeestacionamento.ProblemReportController
import sistemadevagasdeestacionamento.User

class ProblemReportTestDataAndOperations {

    static public void createProblemReport(String title, String sector, String description) {
        def problemReport = new ProblemReport([user: null ,title: title ,sector: sector,description: description])

        def problemReportController = new ProblemReportController()
        problemReportController.save(problemReport)
        problemReportController.response.reset()
    }

}
//#end
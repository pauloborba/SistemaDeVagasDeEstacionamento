package steps

import sistemadevagasdeestacionamento.ProblemReport
import sistemadevagasdeestacionamento.ProblemReportController
import sistemadevagasdeestacionamento.User
/**
 * Created by João Pedro on 19/10/2016.
 */

class ProblemReportTestDataAndOperations {

    static public void createProblemReport(User owner,String title, String sector, String description) {
        def problemReport = new ProblemReport([user: owner ,title: title ,sector: sector,description: description])

        def problemReportController = new ProblemReportController()
        problemReportController.save(problemReport)
        problemReportController.response.reset()
    }

}

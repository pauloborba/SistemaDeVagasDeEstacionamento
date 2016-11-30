package pages

import geb.Page
import geb.navigator.NonEmptyNavigator
import sistemadevagasdeestacionamento.ParkingSpace
import sistemadevagasdeestacionamento.ProblemReport
import steps.InternationalizationHelper

class ProblemReportPage extends Page {
    static url = 'problemReport/index'

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String problemReport = "ProblemReport"
        String problemReportList = helper.getMessage("default.list.label", problemReport)

        title ==~ problemReportList
    }

    def containsProblemReport(String title) {
        def tr = $("tr[data-id='${title}']")
        return tr instanceof NonEmptyNavigator
    }

    def resolve(String title){
        def problemReport = ProblemReport.findByTitle(title)
        $("input[name='${problemReport.id}']").click()
    }

}
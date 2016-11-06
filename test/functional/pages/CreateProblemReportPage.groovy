package pages

import geb.Page
import sistemadevagasdeestacionamento.User
import steps.InternationalizationHelper

/**
 * Created by João Pedro on 03/11/2016.
 */
class CreateProblemReportPage extends Page{


    static url = "/SistemaDeVagasDeEstacionamento/problemReport/create"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String problemReport = "ProblemReport"
        String createProblemReport = helper.getMessage("default.create.label", problemReport)

        title ==~ createProblemReport

    }

    def fillProblemReportInformations(String title,String sector, String description){

        $("input[name='title']").value(title)
        $("input[name='sector']").value(sector)
        $("input[name='description']").value(description)
    }

    def selectCreateProblemReport(){
        $("input", name: "create").click()
    }

    def boolean hasErrors(){
        return $(".errors") != null
    }

}

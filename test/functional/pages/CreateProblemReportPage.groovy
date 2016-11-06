package pages

import geb.Page
import sistemadevagasdeestacionamento.User

/**
 * Created by João Pedro on 03/11/2016.
 */
class CreateProblemReportPage extends Page{

    def titulo = "Create ProblemReport"
    static url = "/SistemaDeVagasDeEstacionamento/problemReport/create"

    static at = {
        title ==~ titulo
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
        if($(".errors") == null){
            return false
        }else{
            return true
        }
    }

}

package pages

import geb.Page

class HomePage extends Page {
    static url = 'home/index'

    static at = {
        title ==~ /Home/
    }

    def goToSuggestions() {
        $("a[name='suggestions']").click()
    }
//#if($ReportParkingSpaceProblem)
    def goToCreateProblemReport() {
        $("a[name='report']").click()
    }
//#end
}

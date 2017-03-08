package pages

import geb.Page

class HomePage extends Page {
    static url = 'home/index'

    static at = {
        title ==~ "Home"
    }

    def goToSuggestionsPage() {
        $("a[name='suggestions']").click()
    }
//#if($ParkingSpaceBooking)

    def goToParkingSpotListPage() {
        $("a[name='spotlist']").click()
    }

//#end
    def goToMyProfilePage() {
        $("a[name='profile']").click()
    }

    def goToParkingSpotListReminderPage() {
        $("a[name='reminder']").click()
    }
//#if($ReportParkingSpaceProblem)
    def goToCreateProblemReport() {
        $("a[name='report']").click()
    }
//#end

    def goToProblemReport() {
        $("a[name='problemReport']").click()
    }
}

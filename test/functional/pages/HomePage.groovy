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

    def goToParkingSpotListPage() {
        $("a[name='spotlist']").click()
    }

    def goToMyProfilePage() {
        $("a[name='profile']").click()
    }

    def goToParkingSpotListReminderPage() {
        $("a[name='reminder']").click()
    }
}

package pages

import geb.Page

class HomePage extends Page {
    static url = 'home/index'

    static at = {
        title ==~ "Home"
    }

    def goToSuggestions() {
        $("a[name='suggestions']").click()
    }

    def goToParkingSpotList() {
        $("a[name='spotlist']").click()
    }

    def goToMyProfile() {
        $("a[name='profile']").click()
    }

    def goToParkingSpotListReminder() {
        $("a[name='reminder']").click()
    }
}

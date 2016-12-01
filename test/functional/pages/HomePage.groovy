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
    def goTo(){
        $("a[name='suggestions']").click()
    }

    def goToHistorico(){
        $("a[name='historico']").click()

    }

    def goToParkingSpotListPage() {
        $("a[name='spotlist']").click()
    }
}

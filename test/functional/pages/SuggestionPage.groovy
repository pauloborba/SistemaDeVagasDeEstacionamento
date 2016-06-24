package pages

import geb.Page
import geb.navigator.EmptyNavigator
import sistemadevagasdeestacionamento.*

class SuggestionPage extends Page {
    static url = 'parkingSpace/suggestion'

    static at = {
        title ==~ /ParkingSpace Listagem/
    }

    def containsParkingSpace(String description) {
        def parkingSpace = ParkingSpace.findByDescription(description)

        def tr = $("tr[data-id='${parkingSpace.id}']")

        return !(tr instanceof EmptyNavigator)
    }

    def selectSectorFilter() {
        $("input[name='sector']").value(true)
    }

    def selectPreferentialFilter() {
        $("input[name='preferential']").value(true)
    }

    def confirmFilterOptions() {
        $("a[name='filter']").click()
    }
}

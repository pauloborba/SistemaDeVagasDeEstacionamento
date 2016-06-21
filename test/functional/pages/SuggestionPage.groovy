package pages

import geb.Page
import sistemadevagasdeestacionamento.*

class SuggestionPage extends Page {
    static url = 'parkingSpace/suggestion'

    static at = {
        title ==~ /ParkingSpace Listagem/
    }

    def containsParkingSpace(String description) {
        def parkingSpace = ParkingSpace.findByDescription(description)

        return $("tr[data-id='${parkingSpace.id}']").size() == 1
    }
}

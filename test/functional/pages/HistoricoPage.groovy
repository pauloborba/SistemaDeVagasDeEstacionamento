package pages

import geb.navigator.NonEmptyNavigator
import sistemadevagasdeestacionamento.*
import geb.Page
import steps.InternationalizationHelper

class HistoricoPage extends Page {
    static url = 'user/historico'

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String history = helper.getMessage("default.history.label")

        title == history
    }

    def goToHome(){
        $("a[name='Principal']").click()
    }
    def emptyHistory () {

        assert $("td[class='${'even'}']").size() == 0

        assert $("td[class='${'odd'}']").size() == 0
    }

    def checkHistory(String description) {
        def parkingSpace = ParkingSpace.findByDescription(description)

        def tr = $("tr[data-id='${parkingSpace.id}']")

        return tr instanceof NonEmptyNavigator
    }

}

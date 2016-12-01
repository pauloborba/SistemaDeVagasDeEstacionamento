package pages

import geb.Page
import geb.navigator.NonEmptyNavigator
import sistemadevagasdeestacionamento.*
import steps.InternationalizationHelper

class SuggestionPage extends Page {
    static url = 'parkingSpace/suggestion'

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String parkingSpace = "ParkingSpace"
        String parkingSpaceList = helper.getMessage("default.list.label", parkingSpace)

        title ==~ parkingSpaceList
    }

    def containsParkingSpace(String description) {
        def parkingSpace = ParkingSpace.findByDescription(description)

        def tr = $("tr[data-id='${parkingSpace.id}']")

        return tr instanceof NonEmptyNavigator
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
    def selectHistoricoFilter(){
        $("input[name='historico']").value(true)
    }

    def noSuggestions(){
        assert $("td[class='${'even'}']").size() == 0

        assert $("td[class='${'odd'}']").size() == 0
    }



}
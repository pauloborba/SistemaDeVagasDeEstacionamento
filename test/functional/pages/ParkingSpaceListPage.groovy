package pages

/**
 * Created by ghls on 07/11/2016.
 */
import geb.Page
import sistemadevagasdeestacionamento.ParkingSpace
import steps.InternationalizationHelper

class ParkingSpaceListPage extends Page {
    static url = 'parkingSpace/index'


    static at = {
        InternationalizationHelper  helper = InternationalizationHelper.instance

        String parkingSpace = "ParkingSpace"
        String pageTitle = helper.getMessage("default.list.label", parkingSpace)

        title ==~ pageTitle
    }

    def goToCreateParkingSpace() {
        $("a[class='create']").click()
    }

    def goToBookParkingSpace(String description){
        def parkingSpace = ParkingSpace.findByDescription(description)
        $("h4[id='${parkingSpace.id}']").click()
    }

    def getBookTime(String description) {
        def parkingSpace = ParkingSpace.findByDescription(description)
        return $("span[id='${parkingSpace.id}']").text()
    }

    def getBookStatusColor(String description) {
        def parkingSpace = ParkingSpace.findByDescription(description)
        return  $("span[id='${parkingSpace.id}']").jquery.css("color")
    }
}

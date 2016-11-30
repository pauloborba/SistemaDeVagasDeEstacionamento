package pages

import geb.Page
import sistemadevagasdeestacionamento.ParkingSpace
import steps.InternationalizationHelper
//#if($Parking Space's reserve report)
class ParkingSpaceList extends Page {
    static url = 'parkingSpace/index'

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String parkingSpace = "ParkingSpace"
        String pageTitle = helper.getMessage("default.list.label", parkingSpace)
        title ==~ pageTitle
    }

    //#if()

    def goToCreateParkingSpacePage(){
        $("a[class='create']").click()
    }

    def goToHomePage(){
        $("a[class='']").click()
    }

    def bookParkingSpace(String description){
        def parkingSpace = ParkingSpace.findByDescription(description)
        $("h4[id='${parkingSpace.getId()}']").click()

    }

    def checkParkingSpace(String description, String currentUser){
        def parkingSpace = ParkingSpace.findByDescription(description)
        def owner = $("h4[id='${parkingSpace.id}']").text()

        if(owner == currentUser){
            return true
        }else{
            return false
        }
    }
//#end
}
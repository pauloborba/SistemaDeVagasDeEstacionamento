package pages

import geb.Page
import sistemadevagasdeestacionamento.ParkingSpace
import steps.InternationalizationHelper

class ParkingSpaceListPage extends Page {
    static url = 'parkingSpace/index'

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String parkingSpace = "ParkingSpace"
        String pageTitle = helper.getMessage("default.list.label", parkingSpace)
        print pageTitle
        title ==~ pageTitle
    }

    def goToCreateParkingSpace(){
        $("a[class='create']").click()
    }

    def bookParkingSpace(String description){
        def parkingSpace = ParkingSpace.findByDescription(description)
        $("h4[id='${parkingSpace.id}']").click()
    }

    def checkSuccessMessage(){
        def message = $("div[class='message']").text()

        if (message.contains("not")){
            return false
        }else{
            return true
        }
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

}

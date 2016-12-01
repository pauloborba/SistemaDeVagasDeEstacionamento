package pages

import geb.Page
import geb.navigator.NonEmptyNavigator
import sistemadevagasdeestacionamento.ParkingSpace
import steps.InternationalizationHelper

class ParkingSpaceListPage extends Page {
    static url = 'parkingSpace/index'

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String parkingSpace = "ParkingSpace"
        String pageTitle = helper.getMessage("default.list.label", parkingSpace)
        title ==~ pageTitle
    }

    //#if($ParkingSpaceBooking)

    def goToCreateParkingSpacePage(){
        $("a[class='create']").click()
    }

    def bookParkingSpace(String description){
        def parkingSpace = ParkingSpace.findByDescription(description)
        $("h4[id='${parkingSpace.getId()}']").click()

    }

    def checkSuccessMessage(){
        def message = $("div[class='message']").text()

        if (!message.contains("not")){
            return true
        }
    }

    def checkParkingSpace(String description, String currentUser){
        String owner = getParkId(description)

        if(owner == currentUser){
            return true
        }else{
            return false
        }
    }

    private String getParkId(String description) {
        def parkingSpace = ParkingSpace.findByDescription(description)
        def owner = $("h4[id='${parkingSpace.id}']").text()
        owner
    }

    def checkPark(description){
        String park = getParkId(description)

        if(park == 'Reserver'){
            return true;

        }else{

            return false;
        }
    }

    private String getParkId(description) {
        def parkingSpace = ParkingSpace.findByDescription(description)
        def park = $("h4[id='${parkingSpace.id}']").text()
        park
    }

    def goToHome(){
        $("a[name='Principal']").click()
    }


}
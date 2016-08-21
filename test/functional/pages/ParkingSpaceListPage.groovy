/*
package pages

import geb.Page
import sistemadevagasdeestacionamento.ParkingSpace
import steps.InternationalizationHelper

class ParkingSpaceListPage extends  Page{
    static url = "parkingSpace/index"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String parkingSpace = "ParkingSpace"
        String parkingSpaceList = helper.getMessage("default.list.label", parkingSpace)

        title ==~ parkingSpaceList
    }


    def searchParkingSpaces(){
        $("input[name='preferential']").click()
        $("input[name='Submit']").click()
    }
    def searchParkingSpacesBySector(){
        $("input[name='sector']").click()
        $("input[name='Submit']").click()
    }
    def verifyPreferential(){


        return $("tr.parking-space[data-preferential='false']").size() == 0

    }

    def verifySector(String sector){

        return $("tr.parking-space[data-sector='${sector}']").size() > 0
    }
    def desc

    def isAvailable(String description){
        desc = description
        def parkingSpace = ParkingSpace.findByDescription(description)
        return $("tr[data-id='${parkingSpace.getId()}']").find('td:first-child').find('a').text() == "Reservar"
    }

    def book(String description){
        def parkingSpace = ParkingSpace.findByDescription(description)
        $("tr[data-id='${parkingSpace.getId()}']").find('td:first-child').find('a').click()
    }

    def verifyMessage() {
        InternationalizationHelper helper = InternationalizationHelper.instance

        $("div.message").text() == helper.getMessage("parkingSpace.booked", desc)
    }

    def verifyFailBookMessage() {
        InternationalizationHelper helper = InternationalizationHelper.instance

        $("div.message").text() == helper.getMessage("parkingSpace.not.booked", desc)
    }
}*/

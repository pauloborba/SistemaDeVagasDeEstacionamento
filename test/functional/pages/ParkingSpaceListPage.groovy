package pages

import geb.Page
import sistemadevagasdeestacionamento.ParkingSpace


class ParkingSpaceListPage extends  Page{

    static url = "parkingSpace/index"

    static at = {
        title ==~ /ParkingSpace Listagem/
    }


    def searchParkingSpaces(boolean pref){
        prefrer = pref
        def parkingSpace = ParkingSpace.findByPreferential(prefer)


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

    def verifyMessage(){
        $("div.message").text() == ("A vaga ${desc} foi reservada com sucesso!" as String)
    }

}
package pages

import geb.Page
import sistemadevagasdeestacionamento.ParkingSpace

class ParkingSpaceListPage extends  Page {
    static url = "parkingSpace/index"

    static at = {
        title ==~ /ParkingSpace Listagem/
    }

    def searchParkingSpaces(boolean pref){
        prefrer = pref
        def parkingSpace = ParkingSpace.findByPreferential(prefer)


    }



}
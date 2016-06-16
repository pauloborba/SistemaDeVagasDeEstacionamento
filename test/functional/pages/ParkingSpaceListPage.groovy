package pages

import geb.Page

class ParkingSpaceListPage extends  Page{
    static url = "parkingSpace/index"

    static at = {
        title == "ParkingSpace List"
    }

    def desc

    def isAvailable(String description){
        def row = $("tr:contains('${description}')")

        $(row.find("td")[0]).find("a:contains('Reservar')") != null
    }

    def book(String description){
        def row = $("tr:contains('${description}')")
        desc = description

        $(row.find("td")[0]).find("a:contains('Reservar')").click()
    }

    def verifyMessage(){
        $("div.message").text() == "The parking space ${desc} was booked with success!"
    }
}
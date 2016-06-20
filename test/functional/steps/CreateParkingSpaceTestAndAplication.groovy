package steps

import cucumber.api.PendingException
import sistemadevagasdeestacionamento.ParkingSpace
import sistemadevagasdeestacionamento.ParkingSpaceController
import sistemadevagasdeestacionamento.User

class CreateParkingSpaceTestAndAplication {




    static public createParkingSpace(String description, String sector, User owner, boolean pref ){
        def controller = new ParkingSpaceController()
        def newParkingSpace = new ParkingSpace([description: description, sector: sector, owner: owner, preferential: pref])
        controller.create()
        controller.save(newParkingSpace)
        controller.response.reset()


    }











}


package steps

import sistemadevagasdeestacionamento.ParkingSpace
import sistemadevagasdeestacionamento.ParkingSpaceController
import sistemadevagasdeestacionamento.User



class VagaTestDataAndOperations {

    static public createParkingSpace(String description, String sector, User owner, boolean pref ){
        def controller = new ParkingSpaceController()
        def newParkingSpace = new ParkingSpace([description: description, sector: sector, owner: owner, preferential: pref])
        controller.create()
        controller.save(newParkingSpace)
        controller.response.reset()
    }
}

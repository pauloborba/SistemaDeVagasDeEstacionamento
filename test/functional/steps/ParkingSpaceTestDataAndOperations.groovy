package steps

import sistemadevagasdeestacionamento.ParkingSpace
import sistemadevagasdeestacionamento.ParkingSpaceController
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.UserController

class ParkingSpaceTestDataAndOperations {

    //#if($ParkingSpaceBooking)
    static public void createParkingSpace(String description, String sector, boolean preferential) {
        def parkingSpaceController = new ParkingSpaceController()
        parkingSpaceController.save(new ParkingSpace([owner:null, description: description, sector: sector, preferential: preferential]))
        parkingSpaceController.response.reset()
    }

    static public void bookParkingSpace(ParkingSpace parkingSpace){
        def parkingSpaceController = new ParkingSpaceController()
        parkingSpaceController.book(parkingSpace)
        parkingSpaceController.response.reset()
    }
    //#end
}

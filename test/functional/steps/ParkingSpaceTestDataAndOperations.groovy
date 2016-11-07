package steps

import sistemadevagasdeestacionamento.Book
import sistemadevagasdeestacionamento.BookController
import sistemadevagasdeestacionamento.ParkingSpace
import sistemadevagasdeestacionamento.ParkingSpaceController
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.UserController

class ParkingSpaceTestDataAndOperations {

    static public void createParkingSpace(String description, String sector, boolean preferential) {
        def parkingSpaceController = new ParkingSpaceController()
        parkingSpaceController.save(new ParkingSpace([owner:null, description: description, sector: sector, preferential: preferential]))
        parkingSpaceController.response.reset()
        print("Vaga " + description + " criada")
    }

    static public void bookParkingSpace(ParkingSpace parkingSpace){
        def parkingSpaceController = new ParkingSpaceController()
        parkingSpaceController.book(parkingSpace)
        parkingSpaceController.response.reset()
    }


    static public void createBook(String parkingSpaceDescription, Integer inHour, Integer outHour) {

        def bookController = new BookController()
        def parkingSpace = ParkingSpace.findByDescription(parkingSpaceDescription)
        bookController.save(new Book([parkingSpace: parkingSpace, inHour: inHour, outHour: outHour]))
        bookController.response.reset()
    }
}

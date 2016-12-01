package steps

import sistemadevagasdeestacionamento.Book
import sistemadevagasdeestacionamento.BookController
import sistemadevagasdeestacionamento.ParkingSpace
import sistemadevagasdeestacionamento.ParkingSpaceController
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.UserController

class ParkingSpaceTestDataAndOperations {

    public static void createParkingSpace(String description, String sector, boolean preferential) {
        def parkingSpaceController = new ParkingSpaceController()
        parkingSpaceController.save(new ParkingSpace([owner:null, description: description, sector: sector, preferential: preferential]))
        parkingSpaceController.response.reset()
    }

    public static void bookParkingSpace(ParkingSpace parkingSpace){
        def parkingSpaceController = new ParkingSpaceController()
        parkingSpaceController.book(parkingSpace)
        parkingSpaceController.response.reset()
    }


    public static void createBook(String username, String parkingSpaceDescription, Integer inHour, Integer outHour) {
        def bookController = new BookController()
        def parkingSpace = ParkingSpace.findByDescription(parkingSpaceDescription)
        def loggedUser = User.findByUsername(username)
        bookController.setOwner(parkingSpace, loggedUser)
        Book bookInstance = new Book([parkingSpace: parkingSpace, inHour: inHour, outHour: outHour])
        bookController.save(bookInstance)
        bookController.response.reset()
    }

    public static void checkBooksTimes() {
        ParkingSpaceController parkingController = new ParkingSpaceController()
        def parkings = ParkingSpace.list()
        parkingController.checkBooksTimes(parkings)
    }

    public static String getRgbColorString(String colorInEnglish) {
        switch (colorInEnglish.toLowerCase()) {
            case "red": return "rgb(255, 0, 0)"
            case "green": return "rgb(0, 255, 0)"
            default: return "rgb(0, 0, 0)"
        }
    }
}

package sistemadevagasdeestacionamento

class ReservedParkingSpace {

    User user
    Date date
    ParkingSpace parkingSpace

    static constraints = {
            user nullable: false
    }
}

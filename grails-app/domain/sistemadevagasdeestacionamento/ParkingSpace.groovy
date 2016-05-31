package sistemadevagasdeestacionamento

class ParkingSpace {
    User owner

    boolean available = { owner == null }

    static constraints = {
    }
}

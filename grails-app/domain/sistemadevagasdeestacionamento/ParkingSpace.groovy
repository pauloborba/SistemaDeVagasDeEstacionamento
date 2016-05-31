package sistemadevagasdeestacionamento

class ParkingSpace {
    User owner
    String description
    String sector
    boolean preferential
    boolean available = { owner == null }

    static constraints = {
        owner nullable: true
        description blank: false
        sector inList: ["CIn", "CCEN", "Área II"]
    }
}

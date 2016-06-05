package sistemadevagasdeestacionamento

class ParkingSpace {
    User owner
    String description
    String sector
    boolean preferential

    static constraints = {
        owner nullable: true
        description nullable: false
        sector inList: ["CIn", "CCEN", "Área II"]
    }

    boolean isAvailable()
    {
        return owner == null
    }
}

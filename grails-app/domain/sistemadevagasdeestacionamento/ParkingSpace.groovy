package sistemadevagasdeestacionamento

class ParkingSpace {
    static hasOne = [book: Book]

    User owner
    String description
    String sector
    boolean preferential

    static constraints = {
        owner nullable: true
        description nullable: false, blank: false, unique: true
        sector inList: ["CIn", "CCEN", "Área II"]
        book nullable: true
    }

    boolean isAvailable()
    {
        return owner == null
    }

    String toString() {
        "${description}"
    }
}
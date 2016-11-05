package sistemadevagasdeestacionamento

class ReservedParkingSpace {

    Integer id
    String description // name of the parking space
    String information // sector of the parking space
    boolean preferential
    Date firstDate
    Date lastDate
    String totalTime

    static constraints = {
        id nullable: false , blank: false
        description nullable: false, blank: false, unique: false
        information inList: ["CIn", "CCEN", "Área II"]
        totalTime blank: false
    }
}

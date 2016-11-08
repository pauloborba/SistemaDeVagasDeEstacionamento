package sistemadevagasdeestacionamento

class ReservedParkingSpace {

    Date firstDate
    Date lastDate
    Integer totalTime

    static belongsTo = [ reserved : ParkingSpace]

    static constraints = {
        totalTime blank: false
    }
}

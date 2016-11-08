package sistemadevagasdeestacionamento

class Book {
    static belongsTo = [parkingSpace: ParkingSpace]

    Integer inHour
    Integer outHour
    String status

    static constraints = {
        parkingSpace unique: true, display: false
        inHour range: 0..23
        outHour range: 0..23
        status nullable: true
    }

    String toString() {
        "${inHour}h - ${outHour}h"
    }
}

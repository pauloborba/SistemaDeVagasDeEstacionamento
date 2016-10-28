package sistemadevagasdeestacionamento

class Book {
    static belongsTo = [parkingSpace: ParkingSpace]

    Integer inHour
    Integer outHour

    static constraints = {
        parkingSpace()
        inHour range: 0..23, validator: { return it >= Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
        outHour range: 0..23, validator: { return it >= Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    }

    String toString() {
        "${inHour}h - ${outHour}h"
    }
}

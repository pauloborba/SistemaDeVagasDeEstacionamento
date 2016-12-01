package sistemadevagasdeestacionamento

class User {
    String username
    String firstName
    String lastName
    String preferredSector
    boolean preferential
    static  hasMany = [historicoReservas:Reserva]

    User() {
        historicoReservas = []
    }

    static constraints = {
        username nullable: false, blank: false, unique: true
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        preferredSector inList: ["CIn", "CCEN", "Área II"]
        preferential nullable: false, blank: false
    }
}

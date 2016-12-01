package sistemadevagasdeestacionamento

class Reserva {

    Date entrada
    Date saida
    static belongsTo = [vaga:ParkingSpace]

    static constraints = {
        entrada nullable: true
        saida nullable: true
    }

}

package steps

import sistemadevagasdeestacionamento.ParkingSpace
import sistemadevagasdeestacionamento.ReservaController
import sistemadevagasdeestacionamento.Reserva

class ReservaTestDataAndOperations {

   static public createReserva(Date entrada, Date saida, ParkingSpace vaga ){
        def controller = new ReservaController()
        def newReserva = new Reserva([entrada: entrada, saida: saida, belongsTo:[vaga: vaga]])
        controller.create()
        controller.save(newReserva)
        controller.response.reset()


    }

    }



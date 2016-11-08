package sistemadevagasdeestacionamento

class ReservedParkingSpaceController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(Integer max) {

        User loggedUser = User.findByUsername(AuthHelper.instance.currentUsername)
        def available = loggedUser.available

        if(available){
            params.max = Math.min(max ?: 10, 100)
            respond ReservedParkingSpace.list(params), model:[reservaInstanceCount: ReservedParkingSpace.count()]
        }else{
            flash.message = "Não há Registro de Reserva de vagas deste usuário."

            redirect(controller: "home", action: "index")
        }


    }

}

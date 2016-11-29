package sistemadevagasdeestacionamento

import cucumber.api.PendingException

class ReservedParkingSpaceController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(Integer max) {

        User loggedUser = User.findByUsername(AuthHelper.instance.currentUsername)

        def reserved = ReservedParkingSpace.findAllWhere(user: loggedUser)

        if (reserved) {
            [reservedInstance: reserved]
        }else{
            flash.message = message(code: 'default.reportPSError.message')

            redirect(controller: "home", action: "index")
        }
    }
}
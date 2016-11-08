package sistemadevagasdeestacionamento

class ReservedParkingSpaceController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ReservedParkingSpace.list(params), model:[reservaInstanceCount: ReservedParkingSpace.count()]
    }

}

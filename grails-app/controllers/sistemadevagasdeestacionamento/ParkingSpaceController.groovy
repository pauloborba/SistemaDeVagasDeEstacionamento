package sistemadevagasdeestacionamento

import grails.converters.JSON
import grails.transaction.Transactional



@Transactional(readOnly = true)
class ParkingSpaceController {
    def index() {
        def parkingSpaces = ParkingSpace.list()

        respond(parkingSpaces, model: [parkingSpaceInstanceCount: parkingSpaces.size()])
    }

    def show(ParkingSpace parkingSpaceInstance) {
        respond(parkingSpaceInstance)
    }

    def create() {
        respond(new ParkingSpace(params))
    }
    //#if($ParkingSpaceBooking)
    def book(ParkingSpace parkingSpaceInstance) {

        if (parkingSpaceInstance){
            def username = AuthHelper.instance.currentUsername
            def user = User.findByUsername(username)

            if (parkingSpaceInstance.isAvailable() && !parkingSpaceInstance.isPreferential()) {

                def lastParkingSpace = ParkingSpace.findByOwner(user)

                if (lastParkingSpace) {
                    unbook(lastParkingSpace)
                    response.reset()
                }

                setHistorico(user, parkingSpaceInstance)

                flash.message = message(code: 'default.avaiable.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])

            }else if (!parkingSpaceInstance.isAvailable()){
                flash.message = message(code: 'default.not.avaiable.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])

            }else if (parkingSpaceInstance.isAvailable() && parkingSpaceInstance.isPreferential()){

                if (user.preferential){

                    def lastParkingSpace = ParkingSpace.findByOwner(user)

                    if (lastParkingSpace) {
                        unbook(lastParkingSpace)
                        response.reset()
                    }

                    setHistorico(parkingSpaceInstance, user)

                    parkingSpaceInstance.save(flush: true)
                    flash.message = message(code: 'default.avaiable.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])
                }else{
                    flash.message = message(code: 'default.not.avaiable.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])
                }

            }

        }else{
            notFound()
        }
        redirect(action: 'index')
    }

    private static void setHistorico(User user, ParkingSpace parkingSpaceInstance) {
        parkingSpaceInstance.owner = user
        def reserva = new Reserva()
        reserva.vaga = parkingSpaceInstance
        user.historicoReservas.add(reserva)
        parkingSpaceInstance.save(flush: true)
        user.save(flush: true)
    }



    @Transactional
    def unbook(ParkingSpace parkingSpace) {
        if(parkingSpace){
            parkingSpace.owner = null
            parkingSpace.save(flush: true)

        }else{
            notFound()
        }
    }
    //#end

    def suggestion() {
        def parkingSpaces = ParkingSpace.list().findAll { parkingSpace ->
            def available = parkingSpace.available
            User loggedUser = User.findByUsername(AuthHelper.instance.currentUsername)
            if (params.containsKey("sector")) {
                def sector = params.sector.toBoolean()

                if (sector) {
                    available = available && (parkingSpace.sector == loggedUser.preferredSector)
                }
            }

            if (params.containsKey("preferential")) {
                def preferential = params.preferential.toBoolean()

                if (preferential)
                {
                    available = available && parkingSpace.preferential
                }
            }
            if(params.containsKey("historico")){
                def historico = params.historico.toBoolean()

                if(historico){

                    available = available && loggedUser.historicoReservas.vaga.contains(parkingSpace)


                }
            }

            return available
        }

        request.withFormat {
            html { respond(parkingSpaces, model: [parkingSpaceInstanceCount: parkingSpaces.size()]) }
            json { render(parkingSpaces as JSON) }
        }
    }

    @Transactional
    def save(ParkingSpace parkingSpaceInstance) {
        if (parkingSpaceInstance != null) {
            if (!parkingSpaceInstance.hasErrors()) {
                parkingSpaceInstance.save(flush: true)

                flash.message = message(code: 'default.created.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])

                redirect(parkingSpaceInstance)
            } else {
                respond(parkingSpaceInstance.errors, view: 'create')
            }
        } else {
            notFound()
        }
    }

    def edit(ParkingSpace parkingSpaceInstance) {
        respond(parkingSpaceInstance)
    }

    @Transactional
    def update(ParkingSpace parkingSpaceInstance) {
        if (parkingSpaceInstance != null) {
            if (!parkingSpaceInstance.hasErrors()) {
                parkingSpaceInstance.save(flush: true)

                flash.message = message(code: 'default.updated.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])

                redirect(parkingSpaceInstance)
            } else {
                respond(parkingSpaceInstance.errors, view: 'edit')
            }
        } else {
            notFound()
        }
    }

    @Transactional
    def delete(ParkingSpace parkingSpaceInstance) {
        if (parkingSpaceInstance != null) {
            parkingSpaceInstance.delete(flush: true)

            flash.message = message(code: 'default.deleted.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])

            redirect(action: "index", method: "GET")
        } else {
            notFound()
        }
    }

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), params.id])

        redirect(action: "index", method: "GET")
    }
}
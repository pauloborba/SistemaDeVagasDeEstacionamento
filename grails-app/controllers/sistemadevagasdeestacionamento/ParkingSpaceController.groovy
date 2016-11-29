package sistemadevagasdeestacionamento

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ParkingSpaceController {
    def currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    def checkBooksTimes(parkingSpaces) {
        parkingSpaces.each {ps ->
            if (ps.book) {
                ps.book.status = (ps.book.outHour < currentHour) ? "red" : "green"
                ps.save(flush: true)
            }
        }

    }


    def index() {
        def parkingSpaces = ParkingSpace.list()

        checkBooksTimes(parkingSpaces)

        respond(parkingSpaces, model: [parkingSpaceInstanceCount: parkingSpaces.size()])
    }

    def show(ParkingSpace parkingSpaceInstance) {
        respond(parkingSpaceInstance)
    }

    def create() {
        respond(new ParkingSpace(params))
    }

    def book(ParkingSpace parkingSpaceInstance) {
        def inHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY), outHour = inHour
        redirect(controller: "book", action: "create",
                params: [parkingSpace: parkingSpaceInstance, inHour: inHour, outHour: outHour], method: "POST")
        // TODO: Exibir mensagem de erro caso não seja possível fazer a reserva
    }

    def suggestion() {
        def parkingSpaces = ParkingSpace.list().findAll { parkingSpace ->
            def available = parkingSpace.available

            if (params.containsKey("sector")) {
                def sector = params.sector.toBoolean()

                if (sector) {
                    User loggedUser = User.findByUsername(AuthHelper.instance.currentUsername)

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
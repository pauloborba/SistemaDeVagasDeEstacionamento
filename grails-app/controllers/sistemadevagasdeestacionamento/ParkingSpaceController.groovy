package sistemadevagasdeestacionamento

import grails.transaction.Transactional

@Transactional(readOnly = true)
class ParkingSpaceController {
    static allowedMethods = [update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        respond ParkingSpace.list(params), model: [parkingSpaceInstanceCount: ParkingSpace.count()]
    }

    def show(ParkingSpace parkingSpaceInstance) {
        respond parkingSpaceInstance
    }

    def create() {
        respond new ParkingSpace(params)
    }

    @Transactional
    def save(ParkingSpace parkingSpaceInstance) {
        if (parkingSpaceInstance == null) {
            if (!parkingSpaceInstance.hasErrors()) {
                parkingSpaceInstance.save flush: true

                flash.message = message(code: 'default.created.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])

                redirect parkingSpaceInstance
            } else {
                respond parkingSpaceInstance.errors, view: 'create'
            }
        } else {
            notFound()
        }
    }

    def edit(ParkingSpace parkingSpaceInstance) {
        respond parkingSpaceInstance
    }

    @Transactional
    def update(ParkingSpace parkingSpaceInstance) {
        if (parkingSpaceInstance != null) {
            if (!parkingSpaceInstance.hasErrors()) {
                parkingSpaceInstance.save flush: true

                flash.message = message(code: 'default.updated.message', args: [message(code: 'ParkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])

                redirect parkingSpaceInstance
            } else {
                respond parkingSpaceInstance.errors, view: 'edit'
            }
        } else {
            notFound()
        }
    }

    @Transactional
    def delete(ParkingSpace parkingSpaceInstance) {
        if (parkingSpaceInstance != null) {
            parkingSpaceInstance.delete flush: true

            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ParkingSpace.label', default: 'ParkingSpace'), parkingSpaceInstance.id])

            redirect action: "index", method: "GET"
        } else {
            notFound()
        }
    }

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'parkingSpace.label', default: 'ParkingSpace'), params.id])

        redirect action: "index", method: "GET"
    }
}
package sistemadevagasdeestacionamento


import grails.converters.JSON
import grails.transaction.Transactional

import org.apache.shiro.SecurityUtils

@Transactional(readOnly = true)
class ParkingSpaceController {

    def index(boolean pref, boolean sector) {
        def parkingSpaces

        if((pref == true)&& (sector == false)) {
            parkingSpaces = ParkingSpace.list().findAll { it.preferential}

        }else if((pref == false)&& ( sector == true)) {
            User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
            parkingSpaces = ParkingSpace.list().findAll { it.sector == loggedUser.preferredSector }

        }else if((pref == false)&& (sector == false)){
            parkingSpaces = ParkingSpace.list()

        } else if(( pref == true)&& (sector == true)){
            User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
            parkingSpaces = ParkingSpace.list().findAll {it.preferential && it.sector == loggedUser.preferredSector }


        }

        respond(parkingSpaces, model: [parkingSpaceInstanceCount: parkingSpaces.size()])
    }

    def show(ParkingSpace parkingSpaceInstance) {
        respond(parkingSpaceInstance)
    }

    def create() {
        respond(new ParkingSpace(params))
    }

def filterSpace(boolean pref, boolean sector){
    def parkingSpaces

    if((pref == true)&& (sector == false)) {
        parkingSpaces = ParkingSpace.list().findAll { it.preferential}

    }else if((pref == false)&& ( sector == true)) {
        User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
        parkingSpaces = ParkingSpace.list().findAll { it.sector == loggedUser.preferredSector }

    }else if((pref == false)&& (sector == false)){
        parkingSpaces = ParkingSpace.list()

    } else if(( pref == true)&& (sector == true)){
        User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
        parkingSpaces = ParkingSpace.list().findAll {it.preferential && it.sector == loggedUser.preferredSector }


    }
return parkingSpaces
}

    def pref(boolean pref, boolean sector){

    if((params.preferential == "on" || pref == true)&& (params.sector == "" ||sector == false)) {

        redirect (action: "index", params: [pref: true, sector: false])

    }else if((params.preferential == "" || pref == false)&& (params.sector == "on" ||sector == true)) {


        redirect (action: "index", params: [pref: false, sector: true])

        }else if((params.preferential == "" || pref == false)&& (params.sector == "" ||sector == false)){
        redirect (action: "index", params: [pref: false, sector: false])

        } else if((params.preferential == "on" || pref == true)&& (params.sector == "on" ||sector == true)){

        redirect (action: "index", params: [pref: true, sector: true])

    }

    }


    @Transactional
    def saveParkingSpace(ParkingSpace ps){
        ps.save(flush:true)
    }

    def bookSpace(Long parkingSpaceId){
        User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
        ParkingSpace parkingSpace = ParkingSpace.findById(parkingSpaceId)
        parkingSpace.setOwner(loggedUser)
        parkingSpace.save(flush: true)
    }

    def book(Long parkingSpaceId){
        bookSpace(parkingSpaceId)

        flash.message = message(code: 'parkingSpace.booked', args: [ParkingSpace.findById(parkingSpaceId).getDescription()])

        redirect(action: "index", method: "GET")
    }

    def suggestion() {
        //User currentUser = User.findByUsername(SecurityUtils.subject.principal)

        def parkingSpaces = ParkingSpace.list().findAll { it.available }

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
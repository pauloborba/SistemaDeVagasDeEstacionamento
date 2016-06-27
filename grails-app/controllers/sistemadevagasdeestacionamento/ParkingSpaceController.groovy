package sistemadevagasdeestacionamento


import grails.converters.JSON
import grails.transaction.Transactional

import org.apache.shiro.SecurityUtils

@Transactional(readOnly = true)
class ParkingSpaceController {



    def index(boolean pref, boolean sector) {
        def parkingSpaces

        if(pref && !sector) {
            parkingSpaces = returnByPreferential()

        }else if(!pref && sector) {
            parkingSpaces = returnBySector()

        }else if(!pref && !sector){
            parkingSpaces = ParkingSpace.list()

        } else if(pref && sector){
            parkingSpaces = returnAll()


        }
        //noinspection GroovyVariableNotAssigned
        respond(parkingSpaces, model: [parkingSpaceInstanceCount: parkingSpaces.size()])
    }

    def show(ParkingSpace parkingSpaceInstance) {
        respond(parkingSpaceInstance)
    }

    def create() {
        respond(new ParkingSpace(params))
    }

    def findSpotOfUser(User userInstance) {
        return ParkingSpace.findByOwner(userInstance)
    }

    def findByUsername(String username){
        ParkingSpace.findAll().toList().each { parkingSpace ->
            if( parkingSpace.getOwner() && parkingSpace.getOwner().getUsername() == username )
                return parkingSpace
        }

        return null
    }

    def filterSpace(boolean pref, boolean sector){
        def parkingSpaces

        if((pref)&& (!sector)) {
            parkingSpaces = ParkingSpace.list().findAll { it.preferential}

        }else if((!pref)&& ( sector)) {
            User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
            parkingSpaces = ParkingSpace.list().findAll { it.sector == loggedUser.preferredSector }

        }else if((!pref)&& (!sector)){
            parkingSpaces = ParkingSpace.list()

        } else {
            User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
            parkingSpaces = ParkingSpace.list().findAll {it.preferential && it.sector == loggedUser.preferredSector }
        }
        return parkingSpaces
    }

    def pref(boolean pref, boolean sector){
        def parkingSpaces
        if(checkPreferential(pref) && !checkSetor(sector)) {
            parkingSpaces = returnByPreferential()

            if(params.preferential == "on") {
                redirect(action: "index", params: [pref: true, sector: false])
            }

        }else if(!checkPreferential(pref) && checkSetor(sector)) {
            parkingSpaces = returnBySector()

            if(params.sector == "on"){
                redirect (action: "index", params: [pref: false, sector: true])
            }

        }else if(!checkPreferential(pref) && !checkSetor(sector)){
            parkingSpaces = ParkingSpace.list()

            if(params.preferential == "" && params.sector == "") {
                redirect(action: "index", params: [pref: false, sector: false])
            }

        } else if(checkPreferential(pref) && checkSetor(sector)){

            parkingSpaces = returnAll()

            if(params.prefential == "on" && params.sector == "on") {
                redirect(action: "index", params: [pref: true, sector: true])
            }
        }
        //noinspection GroovyVariableNotAssigned
        return parkingSpaces

    }

    @Transactional
    def saveParkingSpace(ParkingSpace ps){
        ps.save(flush:true)
    }

    def bookSpace(Long parkingSpaceId){
        def booked = false
        User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
        ParkingSpace parkingSpace = ParkingSpace.findById(parkingSpaceId)
        if(parkingSpace.isAvailable()) {
            parkingSpace.setOwner(loggedUser)
            booked = true
        }
        parkingSpace.save(flush: true)

        return booked
    }

    def book(Long parkingSpaceId){
        def booked = bookSpace(parkingSpaceId)

        if(booked){
            flash.message = message(code: 'parkingSpace.booked', args: [ParkingSpace.findById(parkingSpaceId).getDescription()])
        }else{
            flash.message = message(code: 'parkingSpace.not.booked', args: [ParkingSpace.findById(parkingSpaceId).getDescription()])
        }


        redirect(action: "index", method: "GET")
    }

    def suggestion() {
        def parkingSpaces = ParkingSpace.list().findAll { parkingSpace ->
            def available = parkingSpace.available

            if (params.containsKey("sector")) {
                def sector = params.sector.toBoolean()

                if (sector) {
                    User loggedUser = User.findByUsername(SecurityUtils.subject.principal)

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


    def returnAll(){
        User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
        def spots = ParkingSpace.list().findAll {it.preferential && it.sector == loggedUser.preferredSector }
        return spots
    }

    def returnBySector(){
        User loggedUser = User.findByUsername(SecurityUtils.subject.principal as String)
        def spots = ParkingSpace.list().findAll { it.sector == loggedUser.preferredSector }
        return spots
    }
    def checkPreferential(boolean pref){
        return (params.preferential == "on" || pref);
    }

    def checkSetor(boolean sector){
        return (params.sector == "on" || sector)
    }

    def returnByPreferential(){
        def spots = ParkingSpace.list().findAll { it.preferential}
        return spots
    }
}
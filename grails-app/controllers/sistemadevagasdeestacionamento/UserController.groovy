package sistemadevagasdeestacionamento

import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        respond(User.list(params), model: [userInstanceCount: User.count()])
    }

    def show(User userInstance) {
        respond(userInstance)
    }

    def create() {
        respond(new User(params))
    }

    def lembrete(User userInstance) {
        def parkingSpaceController = new ParkingSpaceController()
        def vaga = parkingSpaceController.findSpotOfUser(userInstance)

        String msg = ""
        if (!vaga) {
            msg = "O usuario não estacionou em nenhuma vaga"
        } else {
            msg = "O usuario estacionou na vaga " + vaga.getDescription()
        }

        flash.message = msg
        redirect userInstance

    }

    @Transactional
    def save(User userInstance) {
        if (userInstance != null) {
            if (!userInstance.hasErrors()) {
                userInstance.save(flush: true)

                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])

                redirect(userInstance)
            } else {
                respond(userInstance.errors, view: 'create')
            }
        } else {
            notFound()
        }
    }

    def edit(User userInstance) {
        respond(userInstance)
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance != null) {
            if (!userInstance.hasErrors()) {
                userInstance.save(flush: true)

                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])

                redirect(userInstance)
            } else {
                respond(userInstance.errors, view: 'edit')
            }
        } else {
            notFound()
        }
    }

    @Transactional
    def delete(User userInstance) {
        if (userInstance != null) {
            userInstance.delete(flush: true)

            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])

            redirect(action: "index", method: "GET")
        } else {
            notFound()
        }
    }

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])

        redirect(action: "index", method: "GET")
    }
}

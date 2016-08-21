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

    // TODO: É necessário internacionalizar as mensagens
    def lembrete(User userInstance) {
        def vaga = ParkingSpace.findByOwner(userInstance)

        flash.message = vaga ? "O usuário estacionou na vaga ${vaga.description}" : "O usuário não estacionou em nenhuma vaga"

        redirect(controller: "home", action: "index")
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
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VagaController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def unbook(Vaga vagaInstance) {
        vagaInstance.removerUsuario()
        vagaInstance.save flush:true

        flash.message = message(code: 'vaga.unbooked.message', args: [vagaInstance.id])

        redirect action:"index"
    }

    def book(Vaga vagaInstance) {
        def usuario = Usuario.findByLogin("admin")

        vagaInstance.setarUsuario(usuario)
        vagaInstance.save flush:true

        flash.message = flash.message = message(code: 'vaga.booked.message', args: [vagaInstance.id])

        redirect action:"index"
    }

    def index(Integer max) {
        def usuario = Usuario.findByLogin("admin")

        if (usuario == null) {
            usuario = new Usuario("admin", "Eu mesmo")
            usuario.save flush: true
        }

        params.max = Math.min(max ?: 10, 100)
        respond Vaga.list(params), model:[vagaInstanceCount: Vaga.count()]
    }

    def show(Vaga vagaInstance) {
        respond vagaInstance
    }

    def create() {
        respond new Vaga(params)
    }

    @Transactional
    def save(Vaga vagaInstance) {
        if (vagaInstance == null) {
            notFound()
            return
        }

        if (vagaInstance.hasErrors()) {
            respond vagaInstance.errors, view:'create'
            return
        }

        vagaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'female.created.message', args: [message(code: 'vaga.label', default: 'Vaga'), vagaInstance.id])
                redirect vagaInstance
            }
            '*' { respond vagaInstance, [status: CREATED] }
        }
    }

    def edit(Vaga vagaInstance) {
        respond vagaInstance
    }

    @Transactional
    def update(Vaga vagaInstance) {
        if (vagaInstance == null) {
            notFound()
            return
        }

        if (vagaInstance.hasErrors()) {
            respond vagaInstance.errors, view:'edit'
            return
        }

        vagaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Vaga.label', default: 'Vaga'), vagaInstance.id])
                redirect vagaInstance
            }
            '*'{ respond vagaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Vaga vagaInstance) {

        if (vagaInstance == null) {
            notFound()
            return
        }

        vagaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Vaga.label', default: 'Vaga'), vagaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vaga.label', default: 'Vaga'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
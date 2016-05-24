

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EstacionamentoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Estacionamento.list(params), model:[estacionamentoInstanceCount: Estacionamento.count()]
    }

    def show(Estacionamento estacionamentoInstance) {
        respond estacionamentoInstance
    }

    def create() {
        respond new Estacionamento(params)
    }

    @Transactional
    def save(Estacionamento estacionamentoInstance) {
        if (estacionamentoInstance == null) {
            notFound()
            return
        }

        if (estacionamentoInstance.hasErrors()) {
            respond estacionamentoInstance.errors, view:'create'
            return
        }

        estacionamentoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'estacionamento.label', default: 'Estacionamento'), estacionamentoInstance.id])
                redirect estacionamentoInstance
            }
            '*' { respond estacionamentoInstance, [status: CREATED] }
        }
    }

    def edit(Estacionamento estacionamentoInstance) {
        respond estacionamentoInstance
    }

    @Transactional
    def update(Estacionamento estacionamentoInstance) {
        if (estacionamentoInstance == null) {
            notFound()
            return
        }

        if (estacionamentoInstance.hasErrors()) {
            respond estacionamentoInstance.errors, view:'edit'
            return
        }

        estacionamentoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Estacionamento.label', default: 'Estacionamento'), estacionamentoInstance.id])
                redirect estacionamentoInstance
            }
            '*'{ respond estacionamentoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Estacionamento estacionamentoInstance) {

        if (estacionamentoInstance == null) {
            notFound()
            return
        }

        estacionamentoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Estacionamento.label', default: 'Estacionamento'), estacionamentoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'estacionamento.label', default: 'Estacionamento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

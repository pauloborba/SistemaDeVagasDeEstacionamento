package sistemadevagasdeestacionamento



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BookController {

    static allowedMethods = [update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Book.list(params), model:[bookInstanceCount: Book.count()]
    }

    def show(Book bookInstance) {
        respond bookInstance
    }

    def setOwner (ParkingSpace parkingSpace, User loggedUser) {
        if (parkingSpace.isAvailable()) {
            parkingSpace.owner = loggedUser
            parkingSpace.save flush:true
        }
    }

    def create() {
        println params.parkingSpace
        def parkingSpace = ParkingSpace.findByDescription(params.parkingSpace)

        User loggedUser = User.findByUsername(AuthHelper.instance.currentUsername)

        setOwner(parkingSpace, loggedUser)

        respond new Book(parkingSpace: parkingSpace, inHour: params.inHour, outHour: params.outHour)
    }

    @Transactional
    def save(Book bookInstance) {
        if (bookInstance != null) {
            if (!bookInstance.hasErrors()) {
                bookInstance.save(flush: true)

                redirect(controller: "parkingSpace", action: "index", method: "GET")
            } else {
                respond(bookInstance.errors, view: 'create')
            }
        } else {
            notFound()
        }

//        if (bookInstance == null) {
//            notFound()
//            return
//        }
//
//        if (bookInstance.hasErrors()) {
//            respond bookInstance.errors, view:'create'
//            return
//        }
//
//        bookInstance.save flush:true
//
//        redirect(controller: "parkingSpace", action: "index", method: "GET")
    }

    def edit(Book bookInstance) {
        respond bookInstance
    }

    @Transactional
    def update(Book bookInstance) {
        if (bookInstance == null) {
            notFound()
            return
        }

        if (bookInstance.hasErrors()) {
            respond bookInstance.errors, view:'edit'
            return
        }

        bookInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Book.label', default: 'Book'), bookInstance.id])
                redirect bookInstance
            }
            '*'{ respond bookInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Book bookInstance) {

        if (bookInstance == null) {
            notFound()
            return
        }

        bookInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Book.label', default: 'Book'), bookInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

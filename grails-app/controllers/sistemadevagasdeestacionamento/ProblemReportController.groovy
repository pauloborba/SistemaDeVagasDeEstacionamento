//#if($ReportParkingSpaceProblem)
package sistemadevagasdeestacionamento

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProblemReportController {
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProblemReport.list(params), model:[problemReportInstanceCount: ProblemReport.count()]
    }

    def show(ProblemReport problemReportInstance) {
        respond problemReportInstance
    }

    def create() {
        //def problem = new ProblemReport(params)
        //problem.user = User.findByUsername(AuthHelper.instance.currentUsername)

        respond new ProblemReport(params)
    }

    @Transactional
    def save(ProblemReport problemReportInstance) {
        if (problemReportInstance == null) {
            notFound()
            return
        }

        problemReportInstance.user = User.findByUsername(AuthHelper.instance.currentUsername)


        if (!problemReportInstance.save(flush:true)) {
            respond problemReportInstance.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'problemReport.label', default: 'ProblemReport'), problemReportInstance.id])
                redirect problemReportInstance
            }
            '*' { respond problemReportInstance, [status: CREATED] }
        }
    }

    def edit(ProblemReport problemReportInstance) {
        respond problemReportInstance
    }


// to set as solved the problem
    @Transactional
    def resolve(ProblemReport problemReportInstance) {
        if (problemReportInstance != null) {
            if (AuthHelper.instance.currentUsername == "master") {
                problemReportInstance.delete(flush: true)

                flash.message = message(
                        code: 'problemReport.resolve.message',
                        args: [message(code: problemReportInstance.title)])

            } else {
                flash.message = message(
                        code: 'problemReport.refuse.message',
                        args: [message(code: AuthHelper.instance.currentUsername)])
            }
            redirect(action: "index", method: "GET")
            return true
        } else {
            notFound()
            return false
        }
    }

    @Transactional
    def update(ProblemReport problemReportInstance) {
        if (problemReportInstance == null) {
            notFound()
            return
        }

        if (problemReportInstance.hasErrors()) {
            respond problemReportInstance.errors, view:'edit'
            return
        }

        problemReportInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ProblemReport.label', default: 'ProblemReport'), problemReportInstance.id])
                redirect problemReportInstance
            }
            '*'{ respond problemReportInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ProblemReport problemReportInstance) {

        if (problemReportInstance == null) {
            notFound()
            return
        }

        problemReportInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ProblemReport.label', default: 'ProblemReport'), problemReportInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'problemReport.label', default: 'ProblemReport'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
//#end
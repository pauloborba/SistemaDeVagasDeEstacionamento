package sistemadevagasdeestacionamento

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProblemReportController {
    def index() {
        def problemReportList = ProblemReport.list()

        respond(problemReportList, model: [problemReportInstanceCount: problemReportList.size()])
    }

    def show(ProblemReport problemReportInstance) {
        respond(problemReportInstance)
    }

    def create() {
        respond(new ProblemReport(params))
    }

    @Transactional
    def save(ProblemReport problemReportInstance) {
        if (problemReportInstance != null) {
            if (!problemReportInstance.hasErrors()) {
                problemReportInstance.save(flush: true)

                flash.message = message(code: 'default.created.message', args: [message(code: 'problemReport.label', default: 'ProblemReport'), problemReportInstance.id])

                redirect(problemReportInstance)
            } else {
                respond(problemReportInstance.errors, view: 'create')
            }
        } else {
            notFound()
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

                flash.message = message(code: 'default.deleted.message', args: [message(code: 'problemReport.label', default: 'ProblemReport'), problemReportInstance.id])

            }
            redirect(action: "index", method: "GET")

            return true
        } else {
            notFound()
            return false
        }
    }

    protected void notFound() {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'problemReport.label', default: 'ProblemReport'), params.id])

        redirect(action: "index", method: "GET")
    }
}

package support

import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import sistemadevagasdeestacionamento.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before() {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()

    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor(appCtx)
    scenarioInterceptor.init()
}

After() {
    AuthHelper.instance.logout()
    //#if($ReportParkingSpaceProblem)
    ProblemReport.list().each { it.delete(flush: true) }
    //#end
    ParkingSpace.list().each { it.delete(flush: true) }

    User.list().each { it.delete(flush: true) }



    scenarioInterceptor.destroy()

    bindingUpdater.remove()
}
package support

import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import sistemadevagasdeestacionamento.*
import steps.ShiroHelper

this.metaClass.mixin(cucumber.api.groovy.Hooks)

Before() {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()

    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor(appCtx)
    scenarioInterceptor.init()
}

After() {
    ShiroHelper.logout()

    def parkingSpaces = ParkingSpace.list()
    parkingSpaces.each { parkingSpace ->
        parkingSpace.delete(flush: true)
    }

    def users = User.list()
    users.each { user ->
        user.delete(flush: true)
    }

    scenarioInterceptor.destroy()

    bindingUpdater.remove()
}
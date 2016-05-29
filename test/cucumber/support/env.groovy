package support


import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor

import static cucumber.api.groovy.Hooks.*

Before () {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()
    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor (appCtx)
    scenarioInterceptor.init ()
}

After () {
    Vaga.list().each { vaga ->
        vaga.delete(flush:true)
    }
    scenarioInterceptor.destroy ()
    bindingUpdater.remove ()
}
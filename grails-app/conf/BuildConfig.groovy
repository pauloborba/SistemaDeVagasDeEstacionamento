grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
grails.server.port.http = 8070

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false
    // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        mavenRepo "http://repository.codehaus.org"
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo "https://repo.grails.org/grails/plugins"
        mavenRepo "http://dl.bintray.com/alkemist/maven/"
    }

    dependencies {

        test "org.grails:grails-datastore-test-support:1.0-grails-2.4"
        compile "org.spockframework:spock-grails-support:0.7-groovy-1.8"
        test "org.gebish:geb-junit4:0.9.2"
        test "org.seleniumhq.selenium:selenium-support:2.39.0"
        test "org.seleniumhq.selenium:selenium-chrome-driver:2.39.0"
        test "org.seleniumhq.selenium:selenium-firefox-driver:2.39.0"
    }

    plugins {
        build ":tomcat:7.0.55"

        compile ":scaffolding:2.1.2"
        compile ':cache:1.1.7'
        compile ":asset-pipeline:1.9.6"

        runtime ":hibernate4:4.3.5.5"
        runtime ":database-migration:1.4.0"
        runtime ":jquery:1.11.1"
        runtime ":shiro:1.2.1"

        test(":cucumber:1.1.0") {
            exclude "spock-core"
        }
        test ":geb:0.9.2"
    }
}
import sistemadevagasdeestacionamento.*
import org.apache.shiro.crypto.hash.Sha512Hash

class BootStrap {
    def shiroSecurityService

    def init = { servletContext ->
        def userRole = new Role(name: "User")
        userRole.save(flush: true, failOnError: true)

        def masterUser = new User(username: "George", passwordHash: new Sha512Hash("123").toHex(), firstName: "George", lastName: "Guedes", preferredSector: "CIn")
        masterUser.addToRoles(userRole)
        masterUser.save(flush: true, failOnError: true)
    }

    def destroy = {
    }
}
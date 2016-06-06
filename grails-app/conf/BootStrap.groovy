import sistemadevagasdeestacionamento.*
import org.apache.shiro.crypto.hash.Sha512Hash

class BootStrap {
    def shiroSecurityService

    def init = { servletContext ->
        def userRole = new Role(name: "User")
        userRole.save(flush: true, failOnError: true)

        def horacioUser = new User(username: "horaciojcfilho", passwordHash: new Sha512Hash("Momozinh@").toHex(), firstName: "Horácio", lastName: "Filho", preferredSector: "CIn")
        horacioUser.addToRoles(userRole)
        horacioUser.save(flush: true, failOnError: true)
    }

    def destroy = {
    }
}
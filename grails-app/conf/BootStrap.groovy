import org.apache.shiro.crypto.hash.Sha512Hash
import sistemadevagasdeestacionamento.*

class BootStrap {
    def shiroSecurityService

    def init = { servletContext ->
        def userRole = new Role(name: "User")
        userRole.save(flush: true)

        def masterUser = new User(username: "master", passwordHash: new Sha512Hash("master").toHex(), firstName: "Usuário", lastName: "Master", preferredSector: "CIn")
        masterUser.addToRoles(userRole)
        masterUser.save(flush: true)
    }

    def destroy = {
    }
}
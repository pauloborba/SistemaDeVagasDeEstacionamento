import sistemadevagasdeestacionamento.*
import org.apache.shiro.crypto.hash.Sha512Hash

class BootStrap {
    def shiroSecurityService

    def init = { servletContext ->
        def userRole = new Role(name: "User")
        userRole.save(flush: true, failOnError: true)

        def horacioUser = new User(username: "hjcf", passwordHash: new Sha512Hash("123").toHex(), firstName: "Horácio", lastName: "Filho", preferredSector: "CIn")
        horacioUser.addToRoles(userRole)
        horacioUser.save(flush: true, failOnError: true)

        def georgeUser = new User(username: "gbg", passwordHash: new Sha512Hash("123").toHex(), firstName: "George", lastName: "Belo", preferredSector: "CCEN")
        georgeUser.addToRoles(userRole)
        georgeUser.save(flush: true, failOnError: true)

        def reuelUser = new User(username: "rjss2", passwordHash: new Sha512Hash("123").toHex(), firstName: "Reuel", lastName: "Jonathan", preferredSector: "CIn")
        reuelUser.addToRoles(userRole)
        reuelUser.save(flush: true, failOnError: true)

        def lucasUser = new User(username: "lbp", passwordHash: new Sha512Hash("123").toHex(), firstName: "Lucas", lastName: "Perrusi", preferredSector: "Área II")
        lucasUser.addToRoles(userRole)
        lucasUser.save(flush: true, failOnError: true)
    }

    def destroy = {
    }
}
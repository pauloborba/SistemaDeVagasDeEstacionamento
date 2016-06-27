
import org.apache.shiro.crypto.hash.Sha512Hash
import sistemadevagasdeestacionamento.*


class BootStrap {
    def shiroSecurityService

    def registerParkingSpaces() {
        def sectors = ParkingSpace.constraints.sector.inList
        sectors.each { sector ->
            (1..6).each { i ->
                def parkingSpace = new ParkingSpace([description: "N${i}-${sector}", sector: sector, preferential: i % 2])
                parkingSpace.save(flush: true)
            }
        }
    }

    def init = { servletContext ->
        def userRole = new Role(name: "User")

        userRole.save(flush: true, failOnError: true)


        def masterUser3 = new User(username: "George", passwordHash: new Sha512Hash("123").toHex(), firstName: "George", lastName: "Guedes", preferredSector: "CCEN")
        masterUser3.addToRoles(userRole)
        masterUser3.save(flush: true)

        def masterUser = new User(username: "master", passwordHash: new Sha512Hash("master").toHex(), firstName: "Usuário", lastName: "Master", preferredSector: "CIn")
        masterUser.addToRoles(userRole)
        masterUser.save(flush: true)

        registerParkingSpaces()

        def masterUser2 = new User(username: "reuel.jonathan", passwordHash: new Sha512Hash("123").toHex(), firstName: "Reuel", lastName: "Jonathan", preferredSector: "CCEN")
        masterUser2.addToRoles(userRole)
        masterUser2.save(flush: true)

    }

    def destroy = {
    }
}
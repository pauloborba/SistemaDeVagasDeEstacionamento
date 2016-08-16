import org.apache.shiro.crypto.hash.Sha512Hash
import sistemadevagasdeestacionamento.*

class BootStrap {
    def shiroSecurityService

    /**
     * Registrando vagas de estacionamento
     */
    def registerParkingSpaces() {
        // Para cada setor, adicionamos 6 vagas de estacionamento
        def sectors = ParkingSpace.constraints.sector.inList
        sectors.each { sector ->
            (1..6).each { i ->
                def parkingSpace = new ParkingSpace([description: "N${i}-${sector}", sector: sector, preferential: i % 2])
                parkingSpace.save(flush: true)
            }
        }
    }

    def init = { servletContext ->
        // Por questões de simplificação, só existe um tipo de usuário no sistema
        def userRole = new Role(name: "User")
        userRole.save(flush: true)

        // Usuário padrão do sistema, esse login quando você desejar logar no sistema
        // sem ter criado um outro usuário previamente
        def masterUser = new User(username: "master", passwordHash: new Sha512Hash("master").toHex(), firstName: "Usuário", lastName: "Master", preferredSector: "CIn")
        masterUser.addToRoles(userRole)
        masterUser.save(flush: true)

        // Registra vagas de estacionamento
        registerParkingSpaces()
    }

    def destroy = {
    }
}
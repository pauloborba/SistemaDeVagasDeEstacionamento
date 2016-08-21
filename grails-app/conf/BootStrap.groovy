import sistemadevagasdeestacionamento.*

class BootStrap {
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
        // Usuário padrão do sistema, esse login quando você desejar logar no sistema
        // sem ter criado um outro usuário previamente
        def masterUser = new User(username: "master", firstName: "Usuário", lastName: "Master", preferredSector: "CIn")
        masterUser.save(flush: true)

        // Registra vagas de estacionamento
        registerParkingSpaces()
    }

    def destroy = {
    }
}
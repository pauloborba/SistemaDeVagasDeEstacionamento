package sistemadevagasdeestacionamento

class User {
    String login
    String firstName
    String lastName
    String preferredSector

    static constraints = {
        preferredSector inList: ["CIn", "CCEN", "Área II"]
    }
}

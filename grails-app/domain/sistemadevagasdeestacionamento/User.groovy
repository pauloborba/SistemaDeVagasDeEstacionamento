package sistemadevagasdeestacionamento

class User {
    String login
    String firstName
    String lastName
    String preferredSector

    static constraints = {
        login unique: true
        firstName nullable: false
        lastName nullable: false
        preferredSector inList: ["CIn", "CCEN", "Área II"]
    }
}

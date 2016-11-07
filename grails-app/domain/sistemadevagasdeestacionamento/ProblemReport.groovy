//#if($ReportParkingSpaceProblem)
package sistemadevagasdeestacionamento

class ProblemReport {
    User user
    String title
    String sector
    String description

    static constraints = {

        user nullable: false
        title nullable: false, blank: false, unique: true
        sector inList: ["CIn", "CCEN", "Área II"]
        description nullable: false, blank: false

    }
}
//#end
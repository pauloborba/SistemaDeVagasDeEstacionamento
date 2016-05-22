/**
 * Created by George on 22/05/16.
 */
class Vaga {
    String descricao
    String setor
    boolean ocupada
    boolean preferencial
    Usuario usuario

    static constraints = {
        descricao blank: false
        usuario nullable: true
        setor inList: ["CCEN", "CIn"]
    }

}


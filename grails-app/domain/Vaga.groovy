/**
 * Created by George on 22/05/16.
 */
class Vaga {

    String descricao
    String setor
    boolean ocupada
    boolean preferencial
    Usuario usuario
    int x
    int y
    static constraints = {
        x blank: false, nullable:false
        y blank: false, nullable:false
        descricao blank: false
        usuario nullable: true
        setor inList: ["CCEN", "CIn"]
    }

}


class Vaga {
    String descricao
    //String setor
    boolean preferencial
    boolean ocupada
    Usuario usuario
    static belongsTo = [setor: Setor]

    static constraints = {
        descricao blank: false
        usuario nullable: true
        //setor inList: ["CIn", "CCEN", "Área II"]
        //estacionamento nullable: false
    }

    void setarUsuario(Usuario usuario) {
        this.usuario = usuario

        ocupada = true
    }

    void removerUsuario() {
        this.usuario = null

        ocupada = false
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 896d75596e237baee4a22e140e097d4e7648be46

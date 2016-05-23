class Usuario {
    String login
    String nome

    Usuario(String login, String nome) {
        this.login = login
        this.nome = nome
    }

    static constraints = {
        login unique: true
        nome blank: false
    }

    @Override
    String toString() {
        return nome
    }
}
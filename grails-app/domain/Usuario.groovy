/**
 * Created by George on 22/05/16.
 */
class Usuario {
    String nome
    String login

    static constraints = {
        login blank: false, unique: true
        nome blank: false
    }
}

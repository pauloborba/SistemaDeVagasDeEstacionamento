package sistemadevagasdeestacionamento

public class AuthController {
    def index = { redirect(action: "login", params: params) }

    def login = {
        [username: params.username]
    }

    def signIn = {
        AuthHelper.instance.login(params.username as String)

        redirect(controller: "home", action: "index")
    }
}

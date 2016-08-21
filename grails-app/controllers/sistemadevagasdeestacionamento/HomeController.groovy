package sistemadevagasdeestacionamento

class HomeController {
    def index() {
        respond(User.findByUsername(AuthHelper.instance.currentUsername))
    }
}
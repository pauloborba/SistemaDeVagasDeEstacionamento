package sistemadevagasdeestacionamento

import org.apache.shiro.SecurityUtils

class HomeController {
    def index() {
        respond(User.findByUsername(SecurityUtils.subject.principal as String))
    }
}
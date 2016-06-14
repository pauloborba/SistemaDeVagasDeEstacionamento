package sistemadevagasdeestacionamento

import org.apache.shiro.SecurityUtils

class HomeController {
    def index() {
        [nome: User.findByUsername(SecurityUtils.subject.principal).firstName]
    }
}

package sistemadevagasdeestacionamento

import org.apache.shiro.SecurityUtils

class HomeController {
    def index() {

//        [userInstance: User.findByUsername(SecurityUtils.subject.principal)]

        respond(User.findByUsername(SecurityUtils.subject.principal as String))

    }
}
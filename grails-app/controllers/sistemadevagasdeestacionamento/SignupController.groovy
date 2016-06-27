package sistemadevagasdeestacionamento

import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash

class SignUpController {
    def index() { }

    def register() {
        String username = params.username

        def user = User.findByUsername(username)

        if (user) {
            flash.message = "User already exists with the username '${username}'"

            redirect(action: 'index')
        } else {
            String password = params.password

            if (password != params.password2) {
                flash.message = "Passwords do not match"

                redirect(action: 'index')
            } else {
                String firstName = params.firstName
                String lastName = params.lastName
                String preferredSector = params.preferredSector

                user = new User(username: username, passwordHash: new Sha512Hash(password).toHex(), firstName: firstName, lastName: lastName, preferredSector: preferredSector)

                if (user.save(flush: true)) {
                    user.addToRoles(Role.findByName('User'))
                    user.save(flush:true)

                    SecurityUtils.subject.login(new UsernamePasswordToken(username, password))

                    redirect(controller: 'home', action: 'index')
                } else {
                    redirect(controller: 'auth', action: 'login')
                }
            }
        }
    }
}
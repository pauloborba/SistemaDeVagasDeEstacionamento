package steps

import sistemadevagasdeestacionamento.UserController
import sistemadevagasdeestacionamento.User

class UserTestDataAndOperations {

    static public void createUser(String username, String firstName, String lastName, String preferredSector,boolean preferential){
        def controllerUser = new UserController()
        def novoUsuario = new User([username: username,firstName: firstName,lastName: lastName,preferredSector: preferredSector,preferential:preferential])
        controllerUser.create()
        controllerUser.save (novoUsuario)
        controllerUser.response.reset()
    }

}

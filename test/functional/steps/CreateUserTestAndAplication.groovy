package steps

import sistemadevagasdeestacionamento.Role
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.UserController


class CreateUserTestAndAplication {
    static public createUser(String username, String passwordHash, String firstName, String lastName, String preferredSector, String roleName){
        def role = Role.findByName(roleName)
        def controllerUser = new UserController()
        def newUser = new User([username: username, passwordHash: passwordHash, firstName: firstName, lastName: lastName, preferredSector: preferredSector,hasMany: [ roles: role, permissions: "perm"] ])
        controllerUser.create()
        controllerUser.save(newUser)
        controllerUser.response.reset()


    }


}

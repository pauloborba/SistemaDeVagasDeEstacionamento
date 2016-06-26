package steps

import sistemadevagasdeestacionamento.Role
import sistemadevagasdeestacionamento.RoleController
import sistemadevagasdeestacionamento.User
class CreateRoleTestAndAplication {


        static public createrole(String name){
            def controllerRole = new RoleController()
            def newRole = new Role(name: name, hasMany: [users: null, permission: "perm"], belongsTo: null )
            controllerRole.create()
            controllerRole.save(newRole)
            controllerRole.response.reset()


        }
}

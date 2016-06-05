import sistemadevagasdeestacionamento.*

class BootStrap {
    def shiroSecurityService

    def init = { servletContext ->
        def userRole = new Role(name: "User")
        userRole.addToPermissions("*:*")
        userRole.save()
    }

    def destroy = {
    }
}
package sistemadevagasdeestacionamento

class SecurityFilters {
    static authenticatedActions = [
        [controller: 'parkingSpace', action: '*', roles: ['User']],
        [controller: 'user', action: '*', roles: ['User']],
        [controller: 'home', action: '*', roles: ['User']]
    ]

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                // Get the map within the authenticated actions which pertain to the current controller and view.
                def authRoles = authenticatedActions.find { (it.controller == controllerName) && ((it.action == '*') || (it.action == actionName)) }

                if (authRoles) {
                    // Perform the access control for each of the roles provided in the authRoles
                    accessControl { authRoles.roles.each { roleName -> role(roleName) } }
                } else {
                    return true
                }
            }
        }
    }
}
package sistemadevagasdeestacionamento

class SecurityFilters {
    // Para que o acesso aos métodos do controlador sejam protegidos pelo Shiro,
    // sendo assim necessário que o usuário esteja logado no sistema para poder
    // usá-los, precisamos definir aqui os controladores e suas ações que desejamos
    // proteger para serem usados somente com login.
    // Quando desejamos proteger todas as ações de um controlador, que geralmente é o que desejamos,
    // usamos o asterísticos que implicará que a proteção será aplicada a todos os métodos do controlador.
    // Como só temos um tipo de usuário no sistema, todas as ações de controlador são permitidas
    // para o usuário logado. Você poderia definir outro tipo de usuário, por exemplo,
    // um usuário administrador, no arquivo BootStrap.groovy adicionando uma nova Rola,
    // e aqui definir controlador e ações que somente esse usuário poderia acessar.
    // Um exemplo disso pode ser um tela de gerencimento do sistema, ou ação de remoção que não
    // devem ser possíveis de serem utilizadas por usuários comuns.
    static authenticatedActions = [
        [controller: 'parkingSpace', action: '*', roles: ['User']],
        [controller: 'user', action: '*', roles: ['User']],
        [controller: 'home', action: '*', roles: ['User']]
    ]

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                def authActions = authenticatedActions.find { it.controller == controllerName && (it.action == '*' || it.action == actionName) }

                if (authActions) {
                    // Realiza o controle de acesso
                    accessControl { authActions.roles.each { roleName -> role(roleName) } }
                } else {
                    // Se nenhuma restrição foi aplicada, podemos permitir o acesso diretamente
                    return true
                }
            }
        }
    }
}
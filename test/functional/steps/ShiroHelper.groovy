package steps

import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha512Hash
import org.apache.shiro.subject.Subject
import org.apache.shiro.util.ThreadContext
import sistemadevagasdeestacionamento.*

class ShiroHelper {
    static def oldMetaClass

    static def signup(username, password, sector) {
        def user = new User(username: username, passwordHash: new Sha512Hash(password).toHex(), firstName: "Primeiro nome", lastName: "Último nome", preferredSector: sector)
        user.addToRoles(Role.findByName('User'))
        user.save(flush:true)
    }

    static def login(username) {
        def metaClassRegistry = GroovySystem.metaClassRegistry

        oldMetaClass = metaClassRegistry.getMetaClass(SecurityUtils)

        metaClassRegistry.removeMetaClass(SecurityUtils)

        def subject = [getPrincipal: { username }, isAuthenticated: { true }] as Subject

        ThreadContext.put(ThreadContext.SECURITY_MANAGER_KEY, [getSubject: { subject } as SecurityManager])

        SecurityUtils.metaClass.static.getSubject = { subject }
    }

    static def logout() {
        GroovySystem.metaClassRegistry.setMetaClass(SecurityUtils, oldMetaClass)
    }
}
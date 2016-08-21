package pages

import geb.Page

class SignUpPage extends Page {
    static url = 'signUp/index'

    static at = {
        title ==~ /Sign up/
    }

    def proceed(String username, String preferredSector) {
        $("input[name='username']").value(username)
        $("input[name='firstName']").value("Primeiro nome")
        $("input[name='lastName']").value("Último nome")
        $("select[name='preferredSector']").value(preferredSector)
        $("input[name='signUp']").click()
    }
}
package pages

import geb.Page

class LoginPage extends Page {
    static url = 'auth/login'

    static at = {
        title ==~ /Login/
    }

    def login(String username, String password){
        $("input[name='username'").value(username)
        $("input[type='submit'").click()
    }
}
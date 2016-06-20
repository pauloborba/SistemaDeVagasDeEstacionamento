package pages

import geb.Page

class InitialPage extends Page {
    static url = 'home'

    static at = {
        title ==~ /Home/
    }

}

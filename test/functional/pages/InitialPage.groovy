package pages

import geb.Page

class InitialPage extends Page {
    static url = 'home'

    static at = {
        title ==~ /Home/
    }

    def clickLembrete() {
        $(".reminder-link").click()
    }

    def verifyMessage(String spot){
        $("div.message").text() == ("O usuario estacionou na vaga ${spot}" as String)
    }

}

package pages

/**
 * Created by gustavo on 07/11/16.
 */

import geb.Page
import sistemadevagasdeestacionamento.*
import steps.InternationalizationHelper

class BookCreatePage extends Page {

    static url = "/book/create"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String book = "Book"
        String pageTitle = helper.getMessage("default.create.label", book)
        title ==~ pageTitle
    }

    def createBook(Integer inHour, Integer outHour) {
        $("select[name='inHour']").value(inHour)
        $("select[name='outHour']").value(outHour)
        $("input[name='create']").click()
    }
}

package pages

import geb.Page

class HomePage extends Page {
    static url = 'home/index'

    static at = {
        title ==~ /Home/
    }

    def goToSuggestions() {
        $("a[name='suggestions']").click()
    }
    //#if($Parking Space's reserve report)
    def boolean hasErrors(){
        return $(".errors") != null
    }
    //end
}

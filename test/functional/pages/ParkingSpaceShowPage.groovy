package pages

import steps.InternationalizationHelper
import geb.Page
//#if($Parking Space's reserve report)
class ParkingSpaceShowPage extends Page {

    static at = {

        InternationalizationHelper helper = InternationalizationHelper.instance

        String parkingSpace = "ParkingSpace"
        String pageTitle = helper.getMessage("default.show.label", parkingSpace)
        print pageTitle
        title ==~ pageTitle
    }

    def goToParkingSpotListPage() {
        $("a[class='list']").click()
    }

    def goToCreateParkingSpacePage() {
        $("a[class='create']").click()
    }
    //#end
}
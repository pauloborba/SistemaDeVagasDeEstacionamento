package pages

//#if($Parking Space's reserve report)
import steps.InternationalizationHelper
import geb.Page

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

}
//#end
package pages

import geb.Page
import steps.InternationalizationHelper;

class CreateParkingSpacePage extends Page {
    static url = "/parkingSpace/create"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String parkingSpace = "ParkingSpace"
        String pageTitle = helper.getMessage("default.list.label", parkingSpace)
        print pageTitle
        title ==~ pageTitle
    }
}

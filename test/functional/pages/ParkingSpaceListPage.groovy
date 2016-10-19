package pages

import geb.Page
import steps.InternationalizationHelper

class ParkingSpaceListPage extends Page {
    static url = 'parkingSpace/index'

    static at = {

        InternationalizationHelper helper = InternationalizationHelper.instance

        String parkingSpace = "ParkingSpace"
        String pageTitle = helper.getMessage("default.list.label", parkingSpace)
        print pageTitle
        title ==~ pageTitle
//        title ==~ /ParkingSpace List/
    }
}

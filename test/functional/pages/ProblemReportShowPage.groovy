package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by João Pedro on 05/03/2017.
 */
class ProblemReportShowPage extends Page{

    static at = {

        InternationalizationHelper helper = InternationalizationHelper.instance

        String problemReport = helper.getMessage("problemReport.label")
        String pageTitle = helper.getMessage("default.show.label", problemReport)

        title ==~ pageTitle
    }

    def goToProblemReportListPage() {
        $("a[class='list']").click()
    }

}

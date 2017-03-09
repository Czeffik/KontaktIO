package page

import geb.Page
import org.openqa.selenium.By


class PanelKontaktLoggedPage extends Page{
    static at={head.isDisplayed()}
    static content={
        head { $('h1') }

        //Button which handling url to go to venues page
        venues{$(By.cssSelector("a[href*='/app/infrastructure/venues'"))}

        //Statemnet which check that page is loaded
        noVenueAdded{$('#el-no-venues-list')}
        listVenueAdded{$('#el-venues-list')}
    }

    //Click button and go to venue page
    private def goToVenues(){
        venues.click()
        waitForLoadPage()
    }

    /*
    Waiting for page loaded and if user has venues created, ListVenueAdded.displayed return true
    but if user has not venues created, noVenueAdded.displayed return true
     */
    private def waitForLoadPage(){
        waitFor{noVenueAdded.displayed || listVenueAdded.displayed}
    }
}

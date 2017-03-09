import filesToUpload.FilesToUpload
import geb.spock.GebReportingSpec
import page.PanelKontaktLogInPage
import page.PanelKontaktLoggedPage
import page.PanelKontaktVenuesPage
import userData.UserData

/*
Acceptance test for creating, reading, updating and removing venues.
 */
class VenueOperationsSpec extends GebReportingSpec {

    //Before each test login to user account and going to venue page
    def setup(){
        to PanelKontaktLogInPage
        loginToKontaktPanel(UserData.userWithCorrectData.username, UserData.userWithCorrectData.password)

        at PanelKontaktLoggedPage
        goToVenues()

        at PanelKontaktVenuesPage
    }

    def 'Should create new venue'(){

        when:'Creating new venue'

        createNewVenue('Los Angeles','Some description', FilesToUpload.fileMediumJoker)

        then:'Checking for statement that venue is created'

        venueModifiedMessage.displayed
    }

    def 'Should back to venue page after create new venue'(){

        when:'Creating new venue and going to venue page'

        createNewVenueAndBackToVenuePage('New York', '', FilesToUpload.fileBigJoker)

        then:'Comparing current URL address with URL address venue page'

        currentUrl=='https://panel.kontakt.io/app/infrastructure/venues'
    }

    def 'Should edit first venue on venue existing list assume that any venue exist'(){

        when:'Editing firs venue of list'

        editVenue('Warsaw', 'Maybe I should write here something', FilesToUpload.fileOtherJoker)


        then:'Checking for statement that venue is edited'

        venueModifiedMessage.displayed
    }

    def 'Should edit first venue on venue existing list assume that any venue exist and back to venues page'(){

        when:'Editing firs venue on list and back to venues page'

        editVenueAndBackToVenuePage('Warsaw','Maybe I should write here something',FilesToUpload.fileOtherJoker)

        then:'Comparing current URL address with URL address venue page'

        currentUrl=='https://panel.kontakt.io/app/infrastructure/venues'
    }

    def 'Should remove first venue from venue existing list assume that any venue exist'(){

        when: 'Removing first venue from venue list'

        deleteVenue()

        then:'Checking for statement that venue is deleted'

        venueModifiedMessage.displayed
    }

    def 'Should skip on tabs in created venue and back to venue page assume any venue is created'(){

        when:'Displaying tabs in existing venue'

        readVenue()

        then:'Comparing current URL address with URL address venue page'

        currentUrl=='https://panel.kontakt.io/app/infrastructure/venues'
    }
}

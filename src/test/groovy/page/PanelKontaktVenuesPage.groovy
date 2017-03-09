package page

import filesToUpload.FilesToUpload
import geb.Page
import org.openqa.selenium.By


class PanelKontaktVenuesPage extends Page {
    static at = {sectionVenues}
    static content = {
        sectionVenues(wait:true){$('h2.js-venues-header')}

        //Button to add new venue
        addVenueButton{$( By.cssSelector( "a[href*='/app/infrastructure/venue/new'")).findAll { it.displayed }}

        //Fields to creating venue
        nameField{$('#venue-name')}
        descriptionField{$('#venue-description')}
        putPhotoField{$('[type = file]')}

        //Venue created message
        venueModifiedMessage{$('div.toast-message')}

        //Button to finish creating venue
        createVenueButton{$('button.button.primary')}
        cancelButton{$('button.button.cancel')}

        //Buttons to edit or remove venue
        pencilButton{($('i.icon.icon-pencil')).findAll{it.displayed}}
        trashButton{($('i.icon.icon-trash')).findAll {it.displayed}}
        deleteVenueOkButton{$('button.button.secondary')}

        //Tabs on selected venue
        venueDevicesTab{$('[data-tab-id=devices]')}
        editVenueDetailsTab{$('[data-tab-id=editor]')}

        //Buttons to assign devices and back from page to assigning
        assignDevicesButton{$('a.right.button.secondary')}
        backFromAvaibleDevicesButton{$('a.back')}

        //Button to read venue
        readVenueButton{$('a.col-name')}


        //Loader container table
        loaderContainer{$('div.loader-container')}
    }

    //Creating new venue which input name of venue, description of venue, and photo file
    def createNewVenue(name, description,photo){
        waitFor {!loaderContainer.displayed}
        addVenueButton.click()
        editAllFields(name,description,photo)
        createVenueButton.click()
        waitFor {venueModifiedMessage.displayed}
    }

    //Creating new venue which input name of venue, description of venue, and photo file and back to venue page
    def createNewVenueAndBackToVenuePage(name,description,photo){
        waitFor {!loaderContainer.displayed}
        addVenueButton.click()
        editAllFields(name,description,photo)
        endCreating()
    }

    //Editing existing venue which is number 1 on list user venues by changing name, description and photo
    def editVenue(name, description, photo){
        waitFor {!loaderContainer.displayed}
        pencilButton[0].click()
        editAllFields(name,description,photo)
        createVenueButton.click()
        waitFor {venueModifiedMessage.displayed}
    }

    //Editing existing venue which is number 1 on list user venues by changing name, description and photo and back to venue page
    def editVenueAndBackToVenuePage(name, description, photo){
        waitFor {!loaderContainer.displayed}
        pencilButton[0].click()
        editAllFields(name,description,photo)
        endCreating()
    }

    //Reading all section existing venue which is number 1 on list user venues
    def readVenue(){
        waitFor {!loaderContainer.displayed}
        readVenueButton[0].click()
        waitFor {!loaderContainer.displayed}
        venueDevicesTab.click()
        waitFor {!loaderContainer.displayed}
        assignDevicesButton.click()
        waitFor {!loaderContainer.displayed}
        backFromAvaibleDevicesButton.click()
        waitFor {!loaderContainer.displayed}
        editVenueDetailsTab.click()
        waitFor {!loaderContainer.displayed}
        cancelButton.click()
        waitFor {!loaderContainer.displayed}
    }

    //Deleting venue which is number 1 on list user venues
    def deleteVenue(){
        waitFor {!loaderContainer.displayed}
        trashButton[0].click()
        deleteVenueOkButton.click()
        waitFor {!loaderContainer.displayed }
    }

    //Puting data to fields on page using when venue is created and edited
    def editAllFields(name,description,photo){
        waitFor {!loaderContainer.displayed}
        nameField=name
        descriptionField=description
        putPhotoField = photo.absolutePath
    }

    /*Finishing creating by click button CREATE/SAVE, wait for loaded page, click CANCEL button
    in order to back to venues page and waiting for page loaded
     */
    def endCreating(){
        createVenueButton.click()
        waitFor {driver.currentUrl!='https://panel.kontakt.io/app/infrastructure/venue/new'&&venueModifiedMessage.displayed&& !loaderContainer.displayed}
        cancelButton.click()
        waitFor {!loaderContainer.displayed }
    }

}


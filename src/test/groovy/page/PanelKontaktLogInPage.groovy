package page

import geb.Page


class PanelKontaktLogInPage extends Page {
    static url = 'https://panel.kontakt.io/signin'
    static at = {heading.isDisplayed()}
    static content= {
        heading { $('h3.form-title') }

        //Fields on sign in page
        emailField { $('input#model-username') }
        passwordField { $('input#model-password') }

        //Buttons
        singInButton { $('input#js-submit') }
        googleButton{$('a.login-button.google')}

        //Google operations
        inputFieldNameGoogle{$('input#Email')}
        nextButtonGoogle{$('input#next')}
        inputFieldPasswordGoogle{$('input#Passwd')}
        logInButtonGoogle{$('input#signIn')}
        logoGoogle{$('div.logo')}
        profileNameGoogle{$('span#email-display')}

        //Error code
        errorCode { $('div#el-errorCode') }

        //Error container
        errorContainer { $('span.error-container') }

        //Main heading when page is logged
        head { $('h1') }

    }

    //Setting email and password and click sing in button
    def loginToKontaktPanel(email, password){
        emailField<<email
        passwordField<<password
        singInButton.click()
        waitForLoadPage()
    }

    //Click google button and input email and google password
    def loginToKontaktPanelViaGoogle(email, password){
        googleButton.click()
        waitFor {logoGoogle.displayed}
        inputFieldNameGoogle<<email
        nextButtonGoogle.click()
        waitFor {profileNameGoogle.displayed}
        inputFieldPasswordGoogle<<password
        logInButtonGoogle.click()
        waitForLoadPage()
    }

    //Waiting for page to load, when head is displayed page is loaded
    private def waitForLoadPage(){
        waitFor {head.displayed}
    }

    //Looking for text condition in error container
    boolean statementFromErrorContainer(String text){
        for (communicat in errorContainer){
            if(communicat.text()==text){
                return true
            }
        }
        return false
    }

}

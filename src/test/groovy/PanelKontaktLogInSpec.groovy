import geb.spock.GebReportingSpec
import page.PanelKontaktLogInPage
import userData.UserData

/*
Acceptance test for login to panel by address https://panel.kontakt.io/signin
Test checking login to system and messages sending to users who introduced wrong email
or wrong password or don't introduced anything.
 */
class PanelKontaktLogInSpec extends GebReportingSpec {

    //Starting from page panel.kontakt.io/signin before every test
    def setup(){
        to PanelKontaktLogInPage
    }

    def 'Should log in to panel successful '(){

        when:'Giving to input fields correct username and password and waiting for page to load'

        loginToKontaktPanel(UserData.userWithCorrectData.username, UserData.userWithCorrectData.password)

        then:"Expected that current address Url is 'https://panel.kontakt.io/app/dashboard'"

        currentUrl=='https://panel.kontakt.io/app/dashboard'
    }

    def "Error Container should return statement 'Username must be a valid email', because wrong email format(only username)"(){

        when:'Giving to input fields nickname like a email and password'

        loginToKontaktPanel(UserData.userWithWrongEmailFormatWithoutAtAndDot.username,UserData.userWithWrongEmailFormatWithoutAtAndDot.password)

        then:"Looking for 'Username must be a valid email' statement in error container"

        statementFromErrorContainer('Username must be a valid email')

    }

    def "Error Container should return statement 'Username must be a valid email', because wrong email format(without @)"(){

        when:'Giving to input fields email and password'

        loginToKontaktPanel(UserData.userWithWrongEmailFormatWithoutAt.username,UserData.userWithWrongEmailFormatWithoutAt.password)

        then:"Looking for 'Username must be a valid email' statement in error container"

        statementFromErrorContainer('Username must be a valid email')
    }

    def "Error Container should return statement 'Username must be a valid email', because wrong email format(doesn't have .sth at the end)"(){

        when:'Giving to input fields email without .sth at the end and password'

        loginToKontaktPanel(UserData.userWithWrongEmailFormatWithoutDotSthAtTheAnd.username,UserData.userWithWrongEmailFormatWithoutDotSthAtTheAnd.password)

        then:"Looking for 'Username must be a valid email' statement in error container"

        statementFromErrorContainer('Username must be a valid email')
    }

    def "Error Container should return statement 'Username must be a valid email', because wrong email format(doesn't have letters after at)"(){

        when:'Giving to input fields email without letters after at and password'

        loginToKontaktPanel(UserData.userWithWrongEmailFormatNotLettersAfterAt.username,UserData.userWithWrongEmailFormatNotLettersAfterAt.password)

        then:"Looking for 'Username must be a valid email' statement in error container"

        statementFromErrorContainer('Username must be a valid email')
    }

    def "Error Container should return statement 'Username is required', because email input field is empty"(){

        when:'Giving to input password field password and email file be empty'

        loginToKontaktPanel(UserData.userWithoutEmail.username,UserData.userWithoutEmail.password)

        then:"Looking for 'Username must be a valid email' statement in error container"

        statementFromErrorContainer('Username is required')
    }

    def "Error container should return statement 'Username must be at most 50 characters', because email is to long(more than 50 char)"(){

        when:'Giving to input fields too long email and password'

        loginToKontaktPanel(UserData.userWithTooLongEmail.username,UserData.userWithTooLongEmail.password)

        then:"Looking for 'Username must be at most 50 characters' statement in error container"

        statementFromErrorContainer('Username must be at most 50 characters')
    }

    def "Error container should return 'Password is required' because password field is empty"(){

        when:'Giving to email input field email and password field be empty'

        loginToKontaktPanel(UserData.userWithoutPassword.username,UserData.userWithoutPassword.password)

        then:"Looking for 'Password is required' statement in error container"

        statementFromErrorContainer('Password is required')
    }

    def "Error container should return 'Password must be between 1 and 50 characters', because password is too long(more than 50 characters)"(){

        when:'Giving to input fields email and too long password'

        loginToKontaktPanel(UserData.userWithTooLongPassword.username,UserData.userWithTooLongPassword.password)

        then:"Looking for 'Password must be between 1 and 50 characters' statement in error container"

        statementFromErrorContainer('Password must be between 1 and 50 characters')
    }

    def "Error code should return 'Wrong email and/or password', because password or email is wrong(not pair in database) but format is correctly"(){

        when:'Giving to input fields email and password formated corectly'

        loginToKontaktPanel(UserData.userFormatedCorectlyButSthNotMatch.username,UserData.userFormatedCorectlyButSthNotMatch.password)

        then:"Expecting 'Wrong email and/or password' in error code"

        errorCode.text() == 'Wrong email and/or password'
    }

    def 'Should login successful via GoogleButton'(){

        when:'Clicking google button and input google data'

        loginToKontaktPanelViaGoogle(UserData.userViaGoogle.username,UserData.userViaGoogle.password)

        then:"Expected that current address Url is 'https://panel.kontakt.io/app/dashboard'"

        currentUrl=='https://panel.kontakt.io/app/dashboard'
    }
}

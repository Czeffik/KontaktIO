package userData

//Users login Data
class UserData {
    static userWithCorrectData = [username:'trzewik93@gmail.com', password:'bhnUIJjVAU', fullname:'Tomasz Trzewik']
    static userWithWrongEmailFormatWithoutAtAndDot = [username:'asdado', password:'sdasda']
    static userWithWrongEmailFormatWithoutAt = [username:'asdadsapl', password:'dsadas']
    static userWithWrongEmailFormatWithoutDotSthAtTheAnd = [username:'asdasda@o2', password:'sadadas']
    static userWithWrongEmailFormatNotLettersAfterAt = [username: '..,.,,@o2.pl', password: 'sadasda']
    static userWithoutEmail = [username:'', password:'adasdasd']
    static userWithTooLongEmail = [username:'12345678901234567890123456789012345678901@yahoo.com', password:'']
    static userWithoutPassword = [username:'asdad@asa.pl', password:'']
    static userWithTooLongPassword = [username:'sadada@o2.pl', password:'123456789009876543211234567890098765432112345678900']
    static userFormatedCorectlyButSthNotMatch = [username:'dasda@oas.sa', password:'dacxvfds']

    static userViaGoogle = [username:'testpanelakontakt2', password:'testpanel']
}

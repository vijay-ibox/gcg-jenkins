import org.fox.jenkins.GmailAndGroovy

def call(String emailAddress, String emailPasscode, String searchSubject) {
    def gmailAndGroovy = new GmailAndGroovy(this)
    def responseOutput = gmailAndGroovy.readEmail(emailAddress, emailPasscode, searchSubject)
    println("Response : ${responseOutput}")
    def valLength = responseOutput.length()
    println("Response length: ${valLength}")
    if(valLength > 2) {
        return "true"
    }
    return "false"
}

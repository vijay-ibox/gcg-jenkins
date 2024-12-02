import org.fox.jenkins.GmailAndGroovy

def call() {
    def gmailAndGroovy = new GmailAndGroovy(this)
    def responseOutput = gmailAndGroovy.readEmail()
    def valLength = responseOutput.length()
    println("Response : ${responseOutput}")
    println("Response length: ${valLength}")
    if(valLength > 2) {
        return "true"
    }
    return "false"
}

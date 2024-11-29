import org.fox.jenkins.GmailAndGroovy

def call() {
    def gmailAndGroovy = new GmailAndGroovy(this)
    if(gmailAndGroovy.readEmail().size() > 0) {
        println(gmailAndGroovy.readEmail())
        return "true"
    }
    return "false"
}

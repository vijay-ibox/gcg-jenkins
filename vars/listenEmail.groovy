import org.fox.jenkins.GmailAndGroovy

def call() {
    def gmailAndGroovy = new GmailAndGroovy(this)
    def responseOutput = gmailAndGroovy.readEmail()
    def valLength = gmailAndGroovy.readEmail().length()
    println("Response length: ${responseOutput}")
    println("Response : ${valLength}")
//    if(gmailAndGroovy.readEmail().length() > 0) {
//        println("Response : " + gmailAndGroovy.readEmail())
//        return "true"
//    }
    return "false"
}

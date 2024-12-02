import org.fox.jenkins.GmailAndGroovy

def call() {
    def gmailAndGroovy = new GmailAndGroovy(this)
    def responseOutput = gmailAndGroovy.readEmail()
    def valLength = responseOutput.length()
    println("Response : ${responseOutput}")
    println("Response lengt: ${valLength}")
//    if(gmailAndGroovy.readEmail().length() > 0) {
//        println("Response : " + gmailAndGroovy.readEmail())
//        return "true"
//    }
    return "false"
}

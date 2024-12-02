import org.fox.jenkins.GmailAndGroovy

def call() {
    def gmailAndGroovy = new GmailAndGroovy(this)
//    println(gmailAndGroovy.readEmail().length())
//    if(gmailAndGroovy.readEmail().length() > 0) {
        println("Response : " + gmailAndGroovy.readEmail())
//        return "true"
//    }
    return "false"
}

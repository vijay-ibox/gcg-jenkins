import org.fox.jenkins.GmailAndGroovy

def call() {
    def gmailAndGroovy = new GmailAndGroovy(this)
//    println(gmailAndGroovy.readEmail().size())
//    if(gmailAndGroovy.readEmail().size() > 0) {
        println("Email Ping Output" + gmailAndGroovy.readEmail())
//        return "true"
//    }
    return "false"
}

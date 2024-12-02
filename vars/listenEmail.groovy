import org.fox.jenkins.GmailAndGroovy

def call() {
    def gmailAndGroovy = new GmailAndGroovy(this)
    StringBuilder builder = new StringBuilder()
    builder.append(gmailAndGroovy.readEmail())
//    println(builder.length())
//    println(builder.length)
//    if(gmailAndGroovy.readEmail().length() > 0) {
//        println("Response : " + gmailAndGroovy.readEmail())
//        return "true"
//    }
    return "false"
}

import org.fox.jenkins.EmailTriggerAPI

def call(String token, String emailAddress) {
    def emailTriggerAPI = new EmailTriggerAPI(this)
    println(emailTriggerAPI.sendEmail(token, emailAddress))
}
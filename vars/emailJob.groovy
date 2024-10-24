import org.fox.jenkins.EmailAPI

def call() {
    def emailAPI = new EmailAPI()
    emailAPI.sendEmail()
    println("Test")
}

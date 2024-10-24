import org.fox.jenkins.EmailAPI

def call() {
    def emailAPI = new EmailAPI()
    def value = emailAPI.sendEmail()
    println(value)
}

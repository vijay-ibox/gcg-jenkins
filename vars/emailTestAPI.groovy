import org.fox.jenkins.EmailTestAPI

def call(String requestUrl, String token, String templateName, String emailAddress, String countryCode, String languageCode) {
    def emailTestAPI = new EmailTestAPI()
    def response = emailTestAPI.sendTestEmail(requestUrl, token, templateName, emailAddress, countryCode, languageCode)
    println(response[0])
}

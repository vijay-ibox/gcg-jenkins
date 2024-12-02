import org.fox.jenkins.EmailTestAPI

def call(String requestUrl, String token, String emailAddress, String countryCode, String languageCode) {
    def emailTestAPI = new EmailTestAPI()
    def response = emailTestAPI.sendTestEmail(requestUrl, token, emailAddress, countryCode, languageCode)
    println("$response.statusCode")
}

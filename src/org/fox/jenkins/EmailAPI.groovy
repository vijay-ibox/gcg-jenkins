// EmailAPI.groovy
 // Replace with your desired package
package org.fox.jenkins

class EmailAPI {
    private String baseUrl  = "https://api.restful-api.dev/objects"
    def jenkins

    EmailAPI(jenkins) {
        this.jenkins = jenkins
    }

    String sendEmail() {
        // Your email sending logic here using JavaMail or another library
        jenkins.println("sendEmail method called")
        String responseText = jenkins.sh(returnStdout: true, script: """
            curl --request POST '$baseUrl' \\
                 --header 'Content-Type: application/json' \\
                 --data-raw '{
                     "name": "Apple MacBook Pro 16",
                     "data": {
                        "year": 2019,
                        "price": 1849.99,
                        "CPU model": "Intel Core i9",
                        "Hard disk size": "1 TB"
                        }
                     }'
        """)

        jenkins.println(responseText)
        return responseText
    }
}


// In your main Groovy file:

//class MyProgram {
//    static void main(String[] args) {
//        def emailAPI = new EmailAPI()
//        emailAPI.sendEmail()
//    }
//}

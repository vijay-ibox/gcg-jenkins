// EmailAPI.groovy
 // Replace with your desired package
package org.fox.jenkins

import java.net.HttpURLConnection
import java.net.URL

class EmailAPI {
    private String baseUrl = "https://api.restful-api.dev/objects"
    def jenkins

    EmailAPI(jenkins) {
        this.jenkins = jenkins
    }

    String sendEmail() {
        // Your email sending logic here using JavaMail or another library
//        jenkins.println("sendEmail method called")
//        String responseText = jenkins.sh(returnStdout: true, script: """
//            curl --request POST '$baseUrl' \\
//                 --header 'Content-Type: application/json' \\
//                 --data-raw '{
//                     "name": "Apple MacBook Pro 16",
//                     "data": {
//                        "year": 2019,
//                        "price": 1849.99,
//                        "CPU model": "Intel Core i9",
//                        "Hard disk size": "1 TB"
//                        }
//                     }'
//        """)
//
//        jenkins.println(responseText)
        return triggerApi("https://api.restful-api.dev/objects")
    }

    String triggerApi(String apiUrl) {
        URL url = new URL(apiUrl)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")

        int responseCode = connection.getResponseCode()
        println("Response Code: " + responseCode)

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))
//            String inputLine
            StringBuffer response = new StringBuffer()

//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine)
//            }
            bufferedReader.close()

            // Print the response
            return println("Response: " + response.toString())
        } else {
            return println("GET request failed")
        }
    }

}

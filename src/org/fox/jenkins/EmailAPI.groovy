// EmailAPI.groovy
 // Replace with your desired package
package org.fox.jenkins

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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
        return triggerApi("https://api.restful-api.dev/objects", "Apple")
    }

    String triggerApi(String apiUrl, String value1) {
//        String data_to_send = "{\n" +
//                "   \"name\": " + value1 + ","
//                "   \"data\": {\n" +
//                "      \"year\": 2019,\n" +
//                "      \"price\": 1849.99,\n" +
//                "      \"CPU model\": \"Intel Core i9\",\n" +
//                "      \"Hard disk size\": \"1 TB\"\n" +
//                "   }\n" +
//                "}";
        Map<String, String> mapValue = new HashMap<>();
        mapValue.put("name",value1);
//        JsonHelper.convertToJsonStringFromMap(mapValue);
        URL url = new URL(apiUrl)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod("POST")
        connection.setDoOutput(true)
        connection.setRequestProperty("Content-Type", "application/json")
//        connection.setRequestProperty("body",data_to_send)
        try {
            DataOutputStream os = new DataOutputStream(connection.getOutputStream())
            os.writeBytes(JsonHelper.convertToJsonStringFromMap(mapValue));
            os.flush();
        } catch (e) {
            println(e)
        }

        int responseCode = connection.getResponseCode()
        println("Response Code: " + responseCode)

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))
            String inputLine
            StringBuffer response = new StringBuffer()

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine)
            }
            bufferedReader.close()

            // Print the response
            return ("Response: " + response.toString())
        } else {
            return ("GET request failed")
        }
    }

}

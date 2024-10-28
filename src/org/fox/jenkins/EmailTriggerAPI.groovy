package org.fox.jenkins

class EmailTriggerAPI {
    def jenkins

    EmailTriggerAPI(jenkins) {
        this.jenkins = jenkins
    }

    def sendEmail(String token, String emailAddress) {
        print(jenkins)
        def responseText = jenkins.sh(returnStdout: true, script: """
        curl --location 'https://api.qa01e.gcp.ford.com/api/email/v3/sendEmail' \\
            --header 'Content-Type: application/json' \\
            --header 'Authorization: $token' \\
            --header 'Application-Id: A4E3C521-41C0-4B91-9B0D-6D11C6325A2D' \\
                --data '{
                    "templateId": "eaas-ford-f150-ssp-na-subscription-expired",
                    "emailAddress": "$emailAddress",
                    "templateValues": {
                        "COUNTRY_CODE": "USA",
                        "INDIVIDUAL_FIRST_NAME": "Shitanshu007",
                        "INDIVIDUAL_LAST_NAME": "Sarode",
                        "INDIVIDUAL_TITLE": "Mr",
                        "LANGUAGE_CODE": "en-US",
                        "BRAND_INDICATOR": "Ford",
                        "BRAND_CODE": "F",
                        "PASSCODE": "PASSCODE"
                    }
                }'
        """)
        echo responseText
        jenkins.println("Response1" + response)
        def response = JsonHelper.parse(responseText)
        print("Response" + response)
        jenkins.println("Response1" + response)
        return responseText
    }
}

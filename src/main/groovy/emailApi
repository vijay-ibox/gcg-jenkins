class EmailAPI {
    private String baseUrl  = "https://api.restful-api.dev/objects"
    private jenkins

    EmailAPI(jenkins) {
        this.jenkins = jenkins
    }

    def sendEmail() {
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
    }



}

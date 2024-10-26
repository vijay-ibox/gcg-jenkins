package org.fox.jenkins

class TestClass {
    def jenkins

    TestClass(jenkins) {
        this.jenkins = jenkins
    }

    def jenkinMethod() {
        print(jenkins)
        String responseText = jenkins.sh(returnStdout: true, script: """curl --location 'https://api.restful-api.dev/objects' \\
--header 'Content-Type: application/json' \\
--data '{
   "name": "Apple MacBook Pro 16",
   "data": {
      "year": 2019,
      "price": 1849.99,
      "CPU model": "Intel Core i9",
      "Hard disk size": "1 TB"
   }
}'""")
        return responseText
    }
}

package org.fox.jenkins

class TestClass {
    def jenkins

    TestClass(jenkins) {
        this.jenkins = jenkins
    }

    def jenkinMethod() {
        print(jenkins)
        return jenkins.sh("""curl --location 'https://api.restful-api.dev/objects'""")
    }
}

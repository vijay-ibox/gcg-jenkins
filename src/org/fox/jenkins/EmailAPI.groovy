// EmailAPI.groovy
 // Replace with your desired package
package org.fox.jenkins

class EmailAPI {
    private jenkins

    EmailAPI(jenkins) {
        this.jenkins = jenkins
    }

    String sendEmail() {
        // Your email sending logic here using JavaMail or another library
        jenkins.println("sendEmail method called")
        return "return sendEmail method"
    }
}


// In your main Groovy file:

//class MyProgram {
//    static void main(String[] args) {
//        def emailAPI = new EmailAPI()
//        emailAPI.sendEmail()
//    }
//}

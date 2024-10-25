// EmailAPI.groovy
 // Replace with your desired package
package org.fox.jenkins

import java.io.*
import org.apache.commons.exec.* 
 //If using commons-exec

class EmailAPI {
    private String baseUrl = "https://api.restful-api.dev/objects"

    String sendEmail() {
        String command = """
            curl --request POST "${baseUrl}" \\
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
        """

        //Choose one of the following methods:

        //Method 1: Using ProcessBuilder (Simpler, built-in)
        def result = executeCommandUsingProcessBuilder(command)


        //Method 2: Using Apache Commons Exec (More robust, add dependency)
        //def result = executeCommandUsingCommonsExec(command)


        if (result.exitCode == 0) {
            return result.stdout
        } else {
            return "Error: ${result.stderr} (Exit code: ${result.exitCode})"
        }
    }

    private static def executeCommandUsingProcessBuilder(String command) {
        def pb = new ProcessBuilder(command.tokenize())
        def process = pb.start()
        def output = new StringBuffer()
        def error = new StringBuffer()

        process.in.eachLine { line -> output.append(line).append('\n') }
        process.err.eachLine { line -> error.append(line).append('\n') }
        process.waitFor()

        return [exitCode: process.exitValue(), stdout: output.toString(), stderr: error.toString()]
    }

    private static def executeCommandUsingCommonsExec(String command) {
        def commandLine = new CommandLine(command)
        def executor = new DefaultExecutor()
        def result = new ByteArrayOutputStream()
        def error = new ByteArrayOutputStream()

        executor.setStreamHandler(new PumpStreamHandler(result, error))
        executor.execute(commandLine)

        return [exitCode: executor.exitValue, stdout: result.toString(), stderr: error.toString()]
    }
}


// In your main Groovy file:

//class MyProgram {
//    static void main(String[] args) {
//        def emailAPI = new EmailAPI()
//        emailAPI.sendEmail()
//    }
//}

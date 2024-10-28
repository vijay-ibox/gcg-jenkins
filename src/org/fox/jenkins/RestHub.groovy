package org.fox.jenkins

class RestHub implements Serializable {
    def statusCode
    def message
    def failure
    def response

    def parseResponse(HttpURLConnection connection) {
        def data = getResponseObject(connection)
        readResponseFromObject(data.response)
    }

    def getResponseObject(HttpURLConnection connection) {
        this.statusCode = connection.responseCode
        this.message = connection.responseMessage
        this.failure = false

        if (statusCode == 200 || statusCode == 201) {
            this.response = connection.content.text
        } else {
            this.failure = true
            this.response = connection.getErrorStream().toString()
        }
        return this
    }

    def readResponseFromObject(data) {
        try {
            return JsonHelper.parse(this.response)
        }
        catch (e) {
            return this.response
        }
    }

    def returnStatusCode(HttpURLConnection connection) {
        return connection.responseCode
    }

    def doHttpRequestWithJson(requestUrl, headers, body, verb, tokenType = null) {
        URL url = new URL(requestUrl)

        HttpURLConnection connection = url.openConnection()

        connection.setRequestMethod(verb)
        if(tokenType == 'Bearer'){
            for (item in headers) {
                if(item.key.toString() == 'Authorization')
                    item.value = 'Bearer '+item.value.toString()
                connection.setRequestProperty(item.key, item.value)
            }
        }
        else{
            for (item in headers) {
                connection.setRequestProperty(item.key, item.value)
            }
        }

        connection.doOutput = true
        if (body.length() > 0) {
            //write the payload to the body of the request
            def writer = new OutputStreamWriter(connection.outputStream)
            // def writer = new ObjectOutputStream(connection.outputStream)
            writer.write(body)
            writer.flush()
            writer.close()
        }

        //make the request
        try {
            connection.connect()
        } catch (e) {
            print("Can not set proxies in RestHub....")
        }

        return connection
    }

    def doGetHttpRequestWithJson(requestUrl, headers, body) {
        return doHttpRequestWithJson(requestUrl, headers, body, "GET")
    }

    def doPostHttpRequestWithJson(requestUrl, headers, body) {
        return doHttpRequestWithJson(requestUrl, headers, body, "POST")
    }

    def doPutHttpRequestWithJson(requestUrl, headers, body) {
        return doHttpRequestWithJson(requestUrl, headers, body, "PUT")
    }

}


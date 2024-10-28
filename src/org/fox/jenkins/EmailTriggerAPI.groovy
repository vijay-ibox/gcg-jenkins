package org.fox.jenkins

class EmailTriggerAPI {
    def jenkins

    EmailTriggerAPI(jenkins) {
        this.jenkins = jenkins
    }

    def sendEmail(String token, String emailAddress) {
        Map<String, Object> mapValue = new HashMap<>();
        mapValue.put("templateId","eaas-ford-f150-ssp-na-subscription-expired");
        mapValue.put("emailAddress",emailAddress);
        Map<String, String> mapValue1 = new HashMap<>();
        mapValue1.put("COUNTRY_CODE","USA");
        mapValue1.put("LANGUAGE_CODE","en-US");
        mapValue1.put("INDIVIDUAL_FIRST_NAME","TestUser");
        mapValue1.put("INDIVIDUAL_LAST_NAME","Test");
        mapValue1.put("PASSCODE","PASSCODE");
        mapValue.put("templateValues", mapValue1);
        def body = JsonHelper.convertToJsonStringFromMap(mapValue)
        def response = openPullRequest("https://api.qa01e.gcp.ford.com/api/email/v3/sendEmail", body, token)
        return response
    }

    def openPullRequest(requestUrl, body, token) {
        def restHub = new RestHub()
        def data = makePostRequest(requestUrl, body, token)
        def message = restHub.parseResponse(data)
        def statusCode = restHub.returnStatusCode(data)
        return [statusCode, message]
    }

    def makePostRequest(requestUrl, body, token) {
        def headers = createHeaders(token)
//        headers['Content-Type'] = 'application/json'
        def response = new RestHub().doPostHttpRequestWithJson(requestUrl, headers, body)
        return response
    }

    def createHeaders(token) {
        def headers = [:]
        headers['Content-Type'] = "application/json"
        headers['Authorization'] = token
        headers['Application-Id'] = "A4E3C521-41C0-4B91-9B0D-6D11C6325A2D"
        return headers
    }
}

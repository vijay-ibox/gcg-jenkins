package org.fox.jenkins

class EmailTestAPI {

    def sendTestEmail(String requestUrl, String token, String templateName, String emailAddress, String countryCode, String languageCode) {
        return callAPI(requestUrl, token, templateName, emailAddress, countryCode, languageCode)
    }

    def callAPI(requestUrl, token, templateName, emailAddress, countryCode, languageCode) {
        def body = getRequestBody(templateName, emailAddress, countryCode, languageCode)
        def restHub = new RestHub()
        def data = makePostRequest(requestUrl, token, body)
        def message = restHub.parseResponse(data)
        def statusCode = restHub.returnStatusCode(data)
        return [statusCode, message]
    }


    def makePostRequest(requestUrl, token, body) {
        def headers = createHeaders(token)
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

    static def getRequestBody(templateName, emailAddress, countryCode, languageCode) {
        Map<String, Object> mapValue = new HashMap<>();
        mapValue.put("templateId", templateName);
        mapValue.put("emailAddress", emailAddress);
        Map<String, String> templateValues = new HashMap<>();
        templateValues.put("COUNTRY_CODE", countryCode);
        templateValues.put("LANGUAGE_CODE", languageCode);
        templateValues.put("INDIVIDUAL_FIRST_NAME", "TestUserFirstName");
        templateValues.put("INDIVIDUAL_LAST_NAME", "TestUserLastName");
        templateValues.put("PASSCODE", "123456");
        mapValue.put("templateValues", templateValues);
        return JsonHelper.convertToJsonStringFromMap(mapValue)
    }
}

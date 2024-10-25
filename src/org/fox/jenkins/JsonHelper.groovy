package org.fox.jenkins

import groovy.json.JsonOutput
import groovy.json.JsonSlurperClassic
import com.cloudbees.groovy.cps.NonCPS

@NonCPS
static def parse(text) {
    def jsonSlurper = new JsonSlurperClassic()
    return jsonSlurper.parseText(text)
}

@NonCPS
static def convertToJsonStringFromMap(map){
    return  JsonOutput.toJson(map)
}

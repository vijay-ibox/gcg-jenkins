def call() {
  EmailAPI emailAPI = new EmailAPI(this)
  println "Calling emailAPI Class"
  emailAPI.sendEmail()
  println "Successfully triggered"
}

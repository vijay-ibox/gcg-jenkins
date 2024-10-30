import org.fox.jenkins.GmailAndGroovy

def call() {
    def test = new GmailAndGroovy(this)
    println(test.readEmail());
    return true
}

import org.fox.jenkins.TestClass

def call() {
    def test = new TestClass(this)
    println(test.jenkinMethod("Apple"));
}

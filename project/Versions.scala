object Versions {
  def cats = "2.10.0"

  def catsTestkit = "2.1.5"

  def scalatest = "3.2.17"

  def scalatestPlus = "3.2.14.0"

  object Plugin {
    // Manually update this value in project/plugins.sbt as well
    def sbtRelease = "1.3.0"

    // Manually update this value in project/plugins.sbt as well
    def sbtCoverage = "2.0.9"

    // Manually update this value in project/plugins.sbt as well
    def sbtMdoc = "2.3.8"

    // Manually update this value in project/plugins.sbt as well
    def scalaFmt = "2.5.2"

    // Manually update this value in project/plugins.sbt as well
    def sbtCiRelease = "1.5.12"

    // Manually update this value in project/plugins.sbt as well
    def mima = "1.1.3"

    // Manually update this value in project/plugins.sbt as well
    def mimaTasty = "0.5.0"
  }
  object Override {
    def protobufJava = "[3.16.3,)"

    def jacksonDatabind = "[2.13.4.2,)"

    def jsoup = "[1.15.3,)"

    def snakeyaml = "[2.0,)"

    def pdfbox = "[2.0.24,)"

    def undertow = "[2.2.24.Final,)"
  }
}

object Versions {
  def cats                = "2.9.0"

  def catsTestkit         = "2.1.5"

  def scalatest           = "3.2.16"

  def scalatestPlus       = "3.2.14.0"

  object Plugin {
    // Manually update this value in project/plugins.sbt as well
    def sbtGithubPackages = "0.5.3"

    // Manually update this value in project/plugins.sbt as well
    def sbtRelease = "1.1.0"

    // Manually update this value in project/plugins.sbt as well
    def sbtCoverage = "2.0.7"
  }
  object Override {
    def protobufJava = "[3.16.3,)"

    def jacksonDatabind = "[2.13.4.2,)"

    def jsoup = "[1.15.3,)"

    def snakeyaml = "[2.0,)"
  }
}

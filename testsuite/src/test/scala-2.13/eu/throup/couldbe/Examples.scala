package eu.throup
package couldbe

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class Examples extends AnyFreeSpec with Matchers {
  "Simple given parameter" - {

    def simpleGivenParameter(implicit message: CouldBeGiven[String]) = message match {
        case IsGiven(actual) => actual
        case IsNotGiven => "This is a default string"
      }

    "A String is given" in {
      implicit val s: String = "This string is given"
      simpleGivenParameter shouldBe "This string is given"
    }

    "No String is given" in {
      simpleGivenParameter shouldBe "This is a default string"
    }
  }
}

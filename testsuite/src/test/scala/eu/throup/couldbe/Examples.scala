package eu.throup
package couldbe

import testfixtures.ExampleFunction
import testfixtures.GivenImplicit
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class Examples extends AnyFreeSpec with Matchers {
  "Simple given parameter" - {
    "A String is given" in {
      import GivenImplicit.string
      ExampleFunction.simpleGivenParameter shouldBe "This string is given"
    }

    "No String is given" in {
      ExampleFunction.simpleGivenParameter shouldBe "This is a default string"
    }
  }
}

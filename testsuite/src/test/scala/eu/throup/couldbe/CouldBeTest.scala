package eu.throup
package couldbe

import testfixtures.ExampleFunction
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CouldBeTest extends AnyFreeSpec with Matchers {
  "Prove it" in {
    // This type definitely does not have a Monad
    class Sam[A]

    val exampleWillInt = ExampleFunction.couldBeAMonad[List]
    val exampleWillString = ExampleFunction.couldBeAMonad[Sam]
    val exampleWillDouble = ExampleFunction.couldBeAMonad[Option]
    val exampleWill = exampleWillInt + exampleWillString + exampleWillDouble

    exampleWill shouldBe
      """I got a Monad!!!!
        |I got nothing!!!
        |I got a Monad!!!!
        |""".stripMargin
  }
}

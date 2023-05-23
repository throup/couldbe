package eu.throup
package couldbe

import testfixtures.ExampleFunction
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CouldHaveTest extends AnyFreeSpec with Matchers {
  "Prove it" in {
    val exampleWillInt    = ExampleFunction.couldHaveSomethingToBe[Int]
    val exampleWillBigInt = ExampleFunction.couldHaveSomethingToBe[BigInt]
    val exampleWillString = ExampleFunction.couldHaveSomethingToBe[String]
    val exampleWill       = exampleWillInt + exampleWillBigInt + exampleWillString

    exampleWill shouldBe
      """I got nothing!!!
        |I got a SomethingToBe!!!!
        |I got nothing!!!
        |""".stripMargin
  }
}

package eu.throup
package couldbe

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CouldBeGivenTest extends AnyFreeSpec with Matchers:
  "Prove it" in {
    given Int    = 7
    given String = "an example String"

    def willItCompile[A: CouldBeGiven]: String =
      CouldBeGiven[A].act(a => s"We got one: $a\n")(() => "Nothing there\n")

    val exampleWillInt    = willItCompile[Int]
    val exampleWillDouble = willItCompile[Double]
    val exampleWillString = willItCompile[String]
    val exampleWill       = exampleWillInt + exampleWillDouble + exampleWillString

    exampleWill shouldBe
      """We got one: 7
        |Nothing there
        |We got one: an example String
        |""".stripMargin
  }

package eu.throup
package couldbe

import cats.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CouldBeTest extends AnyFreeSpec with Matchers:
  "Prove it" in {
    trait SomethingToBe[A]
    given SomethingToBe[BigInt] = new SomethingToBe[BigInt]() {}

    def willItCompile[A: CouldBe[SomethingToBe]]: String =
      CouldBe[SomethingToBe, A].act((td: SomethingToBe[A]) => "I got a SomethingToBe!!!!\n")(() => "I got nothing!!!\n")

    val exampleWillInt    = willItCompile[Int]
    val exampleWillBigInt = willItCompile[BigInt]
    val exampleWillString = willItCompile[String]
    val exampleWill       = exampleWillInt + exampleWillBigInt + exampleWillString

    exampleWill shouldBe
      """I got nothing!!!
        |I got a SomethingToBe!!!!
        |I got nothing!!!
        |""".stripMargin
  }

package eu.throup
package couldbe

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CouldHaveTest extends AnyFreeSpec with Matchers {
  "Prove it" in {
    trait SomethingToBe[A]

    implicit def stb: SomethingToBe[BigInt] = new SomethingToBe[BigInt]() {}

    def willItCompile[A](implicit A: CouldHave[SomethingToBe, A]): String =
      CouldHave[SomethingToBe, A].act((td: SomethingToBe[A]) => "I got a SomethingToBe!!!!\n")(() => "I got nothing!!!\n")

    val exampleWillInt = willItCompile[Int]
    val exampleWillBigInt = willItCompile[BigInt]
    val exampleWillString = willItCompile[String]
    val exampleWill = exampleWillInt + exampleWillBigInt + exampleWillString

    exampleWill shouldBe
      """I got nothing!!!
        |I got a SomethingToBe!!!!
        |I got nothing!!!
        |""".stripMargin
  }
}

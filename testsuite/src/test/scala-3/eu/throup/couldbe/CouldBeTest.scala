package eu.throup
package couldbe

import cats.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CouldBeTest extends AnyFreeSpec with Matchers {
  "Prove it" in {
    def willItCompile[A[_] : CouldBe[Monad]]: String =
      CouldBe[Monad, A].act((monad: Monad[A]) => "I got a Monad!!!!\n")(() => "I got nothing!!!\n")

    // This type definitely does not have a Monad
    class Sam[A]

    val exampleWillInt = willItCompile[List]
    val exampleWillString = willItCompile[Sam]
    val exampleWillDouble = willItCompile[Option]
    val exampleWill = exampleWillInt + exampleWillString + exampleWillDouble

    exampleWill shouldBe
      """I got a Monad!!!!
        |I got nothing!!!
        |I got a Monad!!!!
        |""".stripMargin
  }
}

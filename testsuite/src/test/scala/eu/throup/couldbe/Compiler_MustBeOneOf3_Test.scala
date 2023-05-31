package eu.throup
package couldbe

import testfixtures.CustomType.*
import testfixtures.ExampleFunction
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success, Try}

class Compiler_MustBeOneOf3_Test extends AnyFreeSpec with Matchers {
  "Try error handling" - {
    "Success" - {
      "For Try" in {
        ExampleFunction.willFailIfNotTwo[Try](2) shouldBe Success("That's great!")
      }
      "For Option" in {
        ExampleFunction.willFailIfNotTwo[Option](2) shouldBe Some("That's great!")
      }
      "For Either[_, Throwable]" in {
        ExampleFunction.willFailIfNotTwo[EitherThrowable](2) shouldBe Right("That's great!")
      }
      "For Either[_, String]" in {
        ExampleFunction.willFailIfNotTwo[EitherString](2) shouldBe Right("That's great!")
      }
      "For Either[_, Unit]" in {
        ExampleFunction.willFailIfNotTwo[EitherUnit](2) shouldBe Right("That's great!")
      }
    }

    "Failure" - {
      "For Try" in {
        ExampleFunction.willFailIfNotTwo[Try](99) match {
          case Failure(e) => e.getMessage shouldBe "It's gone wrong!"
          case _          => fail()
        }
      }
      "For Option" in {
        ExampleFunction.willFailIfNotTwo[Option](99) shouldBe None
      }
      "For Either[_, Throwable]" in {
        ExampleFunction.willFailIfNotTwo[EitherThrowable](99) match {
          case Left(e) => e.getMessage shouldBe "It's gone wrong!"
          case _       => fail()
        }
      }
      "For Either[_, String]" in {
        ExampleFunction.willFailIfNotTwo[EitherString](99) shouldBe Left("It's gone wrong!")
      }
      "For Either[_, Unit]" in {
        ExampleFunction.willFailIfNotTwo[EitherUnit](99) shouldBe Left(())
      }
    }
  }
}

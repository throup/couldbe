package eu.throup
package couldbe

import testfixtures.CustomType.*
import testfixtures.ExampleFunction
import cats.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success, Try}

class CompilerTest extends AnyFreeSpec with Matchers {
  "Try error handling" - {
    "Success" - {
      "For Try" in {
        ExampleFunction.willFailIfNotOne[Try](1) shouldBe Success("That's great!")
      }
      "For Option" in {
        ExampleFunction.willFailIfNotOne[Option](1) shouldBe Some("That's great!")
      }
      "For Either[_, Throwable]" in {
        ExampleFunction.willFailIfNotOne[EitherThrowable](1) shouldBe Right("That's great!")
      }
      "For Either[_, String]" in {
        ExampleFunction.willFailIfNotOne[EitherString](1) shouldBe Right("That's great!")
      }
      "For Either[_, Unit]" in {
        ExampleFunction.willFailIfNotOne[EitherUnit](1) shouldBe Right("That's great!")
      }
      "For Id" in {
        ExampleFunction.willFailIfNotOne[Id](1) shouldBe "That's great!"
      }
    }

    "Failure" - {
      "For Try" in {
        ExampleFunction.willFailIfNotOne[Try](2) match {
          case Failure(e) => e.getMessage shouldBe "It's gone wrong!"
          case _ => fail()
        }
      }
      "For Option" in {
        ExampleFunction.willFailIfNotOne[Option](2) shouldBe None
      }
      "For Either[_, Throwable]" in {
        ExampleFunction.willFailIfNotOne[EitherThrowable](2) match {
          case Left(e) => e.getMessage shouldBe "It's gone wrong!"
          case _ => fail()
        }
      }
      "For Either[_, String]" in {
        ExampleFunction.willFailIfNotOne[EitherString](2) shouldBe Left("It's gone wrong!")
      }
      "For Either[_, Unit]" in {
        ExampleFunction.willFailIfNotOne[EitherUnit](2) shouldBe Left(())
      }
      "For Id" in {
        assertThrows[Exception](ExampleFunction.willFailIfNotOne[Id](2))
      }
    }
  }
}

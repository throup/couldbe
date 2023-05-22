package eu.throup
package couldbe

import cats.*
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success, Try}

class CompilerTest extends AnyFreeSpec with Matchers {
  "Try error handling" - {
    type MonadStringFailure[F[_]] = MonadError[F, String]
    type MonadSilentFailure[F[_]] = MonadError[F, Unit]
    type EitherThrowable[A] = Either[Throwable, A]
    type EitherString[A] = Either[String, A]
    type EitherUnit[A] = Either[Unit, A]

    def willFailIfNotOne[F[_] : Monad : CouldBe[MonadThrow] : CouldBe[MonadStringFailure] : CouldBe[MonadSilentFailure]](n: Int) = {
      if (n == 1) Monad[F].pure("That's great!")
      else
        CouldBe[MonadThrow, F].act(_.raiseError(new Exception("It's gone wrong!"))) { () =>
          CouldBe[MonadStringFailure, F].act(_.raiseError("It's gone wrong!")) { () =>
            CouldBe[MonadSilentFailure, F].act(_.raiseError(()))(() => throw new Exception("It's gone wrong!"))
          }
        }
    }

    "Success" - {
      "For Try" in {
        willFailIfNotOne[Try](1) shouldBe Success("That's great!")
      }
      "For Option" in {
        willFailIfNotOne[Option](1) shouldBe Some("That's great!")
      }
      "For Either[_, Throwable]" in {
        willFailIfNotOne[EitherThrowable](1) shouldBe Right("That's great!")
      }
      "For Either[_, String]" in {
        willFailIfNotOne[EitherString](1) shouldBe Right("That's great!")
      }
      "For Either[_, Unit]" in {
        willFailIfNotOne[EitherUnit](1) shouldBe Right("That's great!")
      }
      "For Id" in {
        willFailIfNotOne[Id](1) shouldBe "That's great!"
      }
    }

    "Failure" - {
      "For Try" in {
        willFailIfNotOne[Try](2) match {
          case Failure(e) => e.getMessage shouldBe "It's gone wrong!"
          case _ => fail()
        }
      }
      "For Option" in {
        willFailIfNotOne[Option](2) shouldBe None
      }
      "For Either[_, Throwable]" in {
        willFailIfNotOne[EitherThrowable](2) match {
          case Left(e) => e.getMessage shouldBe "It's gone wrong!"
          case _ => fail()
        }
      }
      "For Either[_, String]" in {
        willFailIfNotOne[EitherString](2) shouldBe Left("It's gone wrong!")
      }
      "For Either[_, Unit]" in {
        willFailIfNotOne[EitherUnit](2) shouldBe Left(())
      }
      "For Id" in {
        assertThrows[Exception](willFailIfNotOne[Id](2))
      }
    }
  }
}

package eu.throup
package couldbe
package testfixtures

import cats.*
import CustomType.*

object ExampleFunction:
  def simpleGivenParameter(using message: CouldBeGiven[String]): String = message.or("This is a default string")

  def couldHaveSomethingToBe[A: CouldHave[SomethingToBe]]: String =
    CouldHave[SomethingToBe, A].act(_ => "I got a SomethingToBe!!!!\n")(() => "I got nothing!!!\n")

  def couldBeAMonad[A[_]: CouldBe[Monad]]: String =
    CouldBe[Monad, A].act(_ => "I got a Monad!!!!\n")(() => "I got nothing!!!\n")

  def willFailIfNotOne[F[_]: Monad: CouldBe[MonadThrow]: CouldBe[MonadStringFailure]: CouldBe[MonadSilentFailure]](
      n: Int
  ): F[String] =
    if n == 1 then Monad[F].pure("That's great!")
    else
      CouldBe[MonadThrow, F].act(_.raiseError(new Exception("It's gone wrong!"))) { () =>
        CouldBe[MonadStringFailure, F].act(_.raiseError("It's gone wrong!")) { () =>
          CouldBe[MonadSilentFailure, F].act(_.raiseError(()))(() => throw new Exception("It's gone wrong!"))
        }
      }

  def willFailIfNotTwo[F[_]: Monad: MustBeOneOf3[MonadThrow, MonadStringFailure, MonadSilentFailure]](
      n: Int
  ): F[String] =
    if n == 2 then Monad[F].pure("That's great!")
    else
      MustBeOneOf3[MonadThrow, MonadStringFailure, MonadSilentFailure, F].act(
        _.raiseError(new Exception("It's gone wrong!"))
      )(_.raiseError("It's gone wrong!"))(_.raiseError(()))

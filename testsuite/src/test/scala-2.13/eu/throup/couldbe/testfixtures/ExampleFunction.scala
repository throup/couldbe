package eu.throup
package couldbe
package testfixtures

import cats.*
import CustomType.*

object ExampleFunction {
  def simpleGivenParameter(implicit message: CouldBeGiven[String]): String = message.or("This is a default string")

  def couldHaveSomethingToBe[A](implicit A: CouldHave[SomethingToBe, A]): String =
    CouldHave[SomethingToBe, A].act(_ => "I got a SomethingToBe!!!!\n")("I got nothing!!!\n")

  def couldBeAMonad[A[_]](implicit A: CouldBe[Monad, A]): String =
    CouldBe[Monad, A].act(_ => "I got a Monad!!!!\n")("I got nothing!!!\n")

  def willFailIfNotOne[F[_]: Monad](n: Int)(implicit
      MT: CouldBe[MonadThrow, F],
      MSF: CouldBe[MonadStringFailure, F],
      MSS: CouldBe[MonadSilentFailure, F]
  ): F[String] =
    if (n == 1) Monad[F].pure("That's great!")
    else
      MT.act(_.raiseError[String](new Exception("It's gone wrong!"))) {
        MSF.act(_.raiseError[String]("It's gone wrong!")) {
          MSS.act(_.raiseError[String](()))(throw new Exception("It's gone wrong!"))
        }
      }

  def willFailIfNotTwo[F[_]: Monad](
      n: Int
  )(implicit MM: MustBeOneOf3[MonadThrow, MonadStringFailure, MonadSilentFailure, F]): F[String] =
    if (n == 2) Monad[F].pure("That's great!")
    else
      MM.act(_.raiseError[String](new Exception("It's gone wrong!")))(_.raiseError[String]("It's gone wrong!"))(
        _.raiseError[String](())
      )
}

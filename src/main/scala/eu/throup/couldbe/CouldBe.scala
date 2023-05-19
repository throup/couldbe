package eu.throup
package couldbe

type CouldBe = [F[_]] =>> [A] =>> CouldBeGiven[F[A]]
object CouldBe:
  def apply[F[_], A](using gof: CouldBeGiven[F[A]]): CouldBe[F][A] = gof

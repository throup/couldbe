package eu.throup
package couldbe

type CouldBe = [F[_[_]]] =>> [A[_]] =>> CouldBeGiven[F[A]]
object CouldBe {
  def apply[F[_[_]], A[_]](using gof: CouldBeGiven[F[A]]): CouldBe[F][A] = gof
}
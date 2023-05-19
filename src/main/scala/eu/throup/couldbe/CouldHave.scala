package eu.throup
package couldbe

type CouldHave = [F[_[_]]] =>> [A[_]] =>> CouldBeGiven[F[A]]
object CouldHave:
  def apply[F[_[_]], A[_]](using gof: CouldBeGiven[F[A]]): CouldHave[F][A] = gof

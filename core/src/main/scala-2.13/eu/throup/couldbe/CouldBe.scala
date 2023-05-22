package eu.throup
package couldbe

object CouldBe {
  def apply[F[_[_]], A[_]](implicit gof: CouldBeGiven[F[A]]): CouldBe[F, A] = gof
}

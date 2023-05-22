package eu.throup
package couldbe

object CouldHave {
  def apply[F[_], A](implicit gof: CouldBeGiven[F[A]]): CouldHave[F, A] = gof
}

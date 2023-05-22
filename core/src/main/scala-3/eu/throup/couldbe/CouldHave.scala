package eu.throup
package couldbe

type CouldHave = [F[_]] =>> [A] =>> CouldBeGiven[F[A]]
object CouldHave {
  def apply[F[_], A](using gof: CouldBeGiven[F[A]]): CouldHave[F][A] = gof
}

package eu.throup
package couldbe

type MustHaveEither = [F[_], G[_]] =>> [A] =>> MustBeGivenEither[F[A], G[A]]
object MustHaveEither:
  def apply[F[_], G[_], A](using gefg: MustBeGivenEither[F[A], G[A]]): MustHaveEither[F, G][A] = gefg

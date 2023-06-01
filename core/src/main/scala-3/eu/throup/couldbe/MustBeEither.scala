package eu.throup
package couldbe

type MustBeEither = [F[_[_]], G[_[_]]] =>> [A[_]] =>> MustBeGivenEither[F[A], G[A]]
object MustBeEither:
  def apply[F[_[_]], G[_[_]], A[_]](using gefg: MustBeGivenEither[F[A], G[A]]): MustBeEither[F, G][A] = gefg

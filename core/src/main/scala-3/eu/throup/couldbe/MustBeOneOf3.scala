package eu.throup
package couldbe

type MustBeOneOf3 = [F[_[_]], G[_[_]], H[_[_]]] =>> [A[_]] =>> MustBeGivenOneOf3[F[A], G[A], H[A]]
object MustBeOneOf3:
  def apply[F[_[_]], G[_[_]], H[_[_]], A[_]](using
      gefgh: MustBeGivenOneOf3[F[A], G[A], H[A]]
  ): MustBeOneOf3[F, G, H][A] = gefgh

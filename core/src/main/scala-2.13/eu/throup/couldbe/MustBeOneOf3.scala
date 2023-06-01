package eu.throup
package couldbe

object MustBeOneOf3 {
  def apply[F[_[_]], G[_[_]], H[_[_]], A[_]](implicit
      gefgh: MustBeGivenOneOf3[F[A], G[A], H[A]]
  ): MustBeOneOf3[F, G, H, A] = gefgh
}

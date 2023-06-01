package eu.throup

package object couldbe {
  type CouldBe[F[_[_]], A[_]] = CouldBeGiven[F[A]]
  type CouldHave[F[_], A]     = CouldBeGiven[F[A]]

  type MustBeOneOf3[F[_[_]], G[_[_]], H[_[_]], A[_]] = MustBeGivenOneOf3[F[A], G[A], H[A]]
}

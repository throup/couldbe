package eu.throup

package object couldbe {
  type CouldBe[F[_[_]], A[_]] = CouldBeGiven[F[A]]
  type CouldHave[F[_], A]     = CouldBeGiven[F[A]]
}

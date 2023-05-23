package eu.throup
package couldbe

import cats.{Monoid, Semigroup}

class MonoidCouldBeGiven[A: Semigroup] extends Monoid[CouldBeGiven[A]] {
  override def combine(x: CouldBeGiven[A], y: CouldBeGiven[A]): CouldBeGiven[A] =
    (x, y) match {
      case (IsGiven(a), IsGiven(b))    => IsGiven(Semigroup[A].combine(a, b))
      case (_: IsGiven[?], IsNotGiven) => x
      case (IsNotGiven, _: IsGiven[?]) => y
      case _                           => empty
    }

  override def empty: CouldBeGiven[A] = CouldBeGiven.isNotGiven
}

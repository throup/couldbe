package eu.throup
package couldbe

import cats.{Hash, PartialOrder}

class EqCouldBeGiven[A: HashOrHashCode : PartialOrderOrEq] extends Hash[CouldBeGiven[A]] with PartialOrder[CouldBeGiven[A]] {
  override def hash(x: CouldBeGiven[A]): Int = x.gift.map(HashOrHashCode.h(_)).getOrElse(0)

  override def partialCompare(x: CouldBeGiven[A], y: CouldBeGiven[A]): Double =
    (x, y) match {
      case (IsNotGiven, _: IsGiven[?]) => -1.0
      case (_: IsGiven[?], IsNotGiven) => 1.0
      case (IsNotGiven, IsNotGiven) => 0.0
      case (IsGiven(a), IsGiven(b)) => PartialOrderOrEq.p(a, b)
    }
}

package eu.throup
package couldbe

import cats.*

trait EqHierarchy[A] extends Hash[A], PartialOrder[A]
given [A: HashOrHashCode: PartialOrderOrEq]: EqHierarchy[CouldBeGiven[A]] =
  new EqHierarchy[CouldBeGiven[A]] {
    override def hash(x: CouldBeGiven[A]): Int = x.gift.map(HashOrHashCode.h(_)).getOrElse(0)

    override def partialCompare(x: CouldBeGiven[A], y: CouldBeGiven[A]): Double =
      (x, y) match {
        case (IsNotGiven, _: IsGiven[?]) => -1.0
        case (_: IsGiven[?], IsNotGiven) => 1.0
        case (IsNotGiven, IsNotGiven) => 0.0
        case (IsGiven(a), IsGiven(b)) => PartialOrderOrEq.p(a, b)
      }
  }

given [A: HashOrHashCode: PartialOrderOrEq]: EqHierarchy[IsGiven[A]] =
  new EqHierarchy[IsGiven[A]] {
    override def hash(x: IsGiven[A]): Int = HashOrHashCode.h(x.get)

    override def partialCompare(x: IsGiven[A], y: IsGiven[A]): Double = PartialOrderOrEq.p(x.get, y.get)
  }

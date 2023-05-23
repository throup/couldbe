package eu.throup
package couldbe

import cats.{Hash, PartialOrder}

class EqIsGiven[A: HashOrHashCode : PartialOrderOrEq] extends Hash[IsGiven[A]] with PartialOrder[IsGiven[A]] {
  override def hash(x: IsGiven[A]): Int = HashOrHashCode.h(x.get)

  override def partialCompare(x: IsGiven[A], y: IsGiven[A]): Double = PartialOrderOrEq.p(x.get, y.get)
}

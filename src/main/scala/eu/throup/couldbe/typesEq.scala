package eu.throup
package couldbe

import cats.*

trait EqHierarchy[A] extends Hash[A], PartialOrder[A]
given [A: Eq: CouldBe[Hash]: CouldBe[PartialOrder]]: EqHierarchy[CouldBeGiven[A]] =
  new EqHierarchy[CouldBeGiven[A]]:
    override def hash(x: CouldBeGiven[A]): Int =
      CouldBe[Hash, A].act(H => x.gift.map(H.hash(_)).getOrElse(0))(() => x.hashCode())
    override def partialCompare(x: CouldBeGiven[A], y: CouldBeGiven[A]): Double =
      (x, y) match
        case (IsNotGiven, _: IsGiven[?])    => -1.0
        case (_: IsGiven[?], IsNotGiven)    => 1.0
        case (IsNotGiven, IsNotGiven) => 0.0
        case (IsGiven(a), IsGiven(b)) =>
          CouldBe[PartialOrder, A].act(_.partialCompare(a, b))(() =>
            if Eq.eqv(a, b) then 0 else Double.NaN
          )
given [A: Eq: CouldBe[Hash]: CouldBe[PartialOrder]]: EqHierarchy[IsGiven[A]] =
  new EqHierarchy[IsGiven[A]]:
    override def hash(x: IsGiven[A]): Int =
      CouldBe[Hash, A].act(H => x.gift.map(H.hash(_)).getOrElse(0))(() => x.hashCode())

    override def partialCompare(x: IsGiven[A], y: IsGiven[A]): Double = {
      CouldBe[PartialOrder, A].act(_.partialCompare(x.get, y.get))(() =>
        if Eq.eqv(x.get, y.get) then 0 else Double.NaN
      )
    }

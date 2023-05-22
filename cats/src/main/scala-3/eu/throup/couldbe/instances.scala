package eu.throup
package couldbe

import cats.*

trait ShowOrToString[-A: CouldHave[Show]] {
  def s(a: A): String = CouldHave[Show, A].act(_.show(a))(() => a.toString)
}
object ShowOrToString {
  given[A: CouldHave[Show]]: ShowOrToString[A] = new ShowOrToString[A] {}

  def apply[A: ShowOrToString]: ShowOrToString[A] = summon[ShowOrToString[A]]

  def s[A: ShowOrToString](a: A): String = ShowOrToString[A].s(a)
}

trait HashOrHashCode[A: CouldHave[Hash]] {
  def h(a: A): Int = CouldHave[Hash, A].act(_.hash(a))(() => a.hashCode())
}
object HashOrHashCode {
  given[A: CouldHave[Hash]]: HashOrHashCode[A] = new HashOrHashCode[A] {}

  def apply[A: HashOrHashCode]: HashOrHashCode[A] = summon[HashOrHashCode[A]]

  def h[A: HashOrHashCode](a: A): Int = HashOrHashCode[A].h(a)
}

trait EqOrEquals[A: CouldHave[Eq]] {
  def e(x: A, y: A): Boolean = CouldHave[Eq, A].act(_.eqv(x, y))(() => x.equals(y) && y.equals(x))
}
object EqOrEquals {
  given[A: CouldHave[Eq]]: EqOrEquals[A] = new EqOrEquals[A] {}

  def apply[A: EqOrEquals]: EqOrEquals[A] = summon[EqOrEquals[A]]

  def e[A: EqOrEquals](x: A, y: A): Boolean = EqOrEquals[A].e(x, y)
}

trait PartialOrderOrEq[A: CouldHave[PartialOrder]: EqOrEquals] {
  def p(x: A, y: A): Double =
    CouldHave[PartialOrder, A].act(_.partialCompare(x, y))(() => if EqOrEquals.e(x, y) then 0.0 else Double.NaN)
}
object PartialOrderOrEq {
  given[A: CouldHave[Eq]]: PartialOrderOrEq[A] = new PartialOrderOrEq[A] {}

  def apply[A: PartialOrderOrEq]: PartialOrderOrEq[A] = summon[PartialOrderOrEq[A]]

  def p[A: PartialOrderOrEq](x: A, y: A): Double = PartialOrderOrEq[A].p(x, y)
}

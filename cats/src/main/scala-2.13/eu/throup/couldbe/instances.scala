package eu.throup
package couldbe

import cats.*

trait ShowOrToString[A] {
  def s(a: A)(implicit S: CouldHave[Show, A]): String = CouldHave[Show, A].act(_.show(a))(() => a.toString)
}
object ShowOrToString {
  implicit def implicitShowOrToString[A](implicit S: CouldHave[Show, A]): ShowOrToString[A] = new ShowOrToString[A] {}

  def apply[A: ShowOrToString]: ShowOrToString[A] = implicitly[ShowOrToString[A]]

  def s[A: ShowOrToString](a: A): String = ShowOrToString[A].s(a)
}

trait HashOrHashCode[A] {
  def h(a: A)(implicit H: CouldHave[Hash, A]): Int = CouldHave[Hash, A].act(_.hash(a))(() => a.hashCode())
}
object HashOrHashCode {
  implicit def implicitHashOrHashCode[A](implicit H: CouldHave[Hash, A]): HashOrHashCode[A] = new HashOrHashCode[A] {}

  def apply[A: HashOrHashCode]: HashOrHashCode[A] = implicitly[HashOrHashCode[A]]

  def h[A: HashOrHashCode](a: A): Int = HashOrHashCode[A].h(a)
}

trait EqOrEquals[A] {
  def e(x: A, y: A)(implicit E: CouldHave[Eq, A]): Boolean = CouldHave[Eq, A].act(_.eqv(x, y))(() => (x == y) && (y == x))
}
object EqOrEquals {
  implicit def implicitEqOrEquals[A](implicit E: CouldHave[Eq, A]): EqOrEquals[A] = new EqOrEquals[A] {}

  def apply[A: EqOrEquals]: EqOrEquals[A] = implicitly[EqOrEquals[A]]

  def e[A: EqOrEquals](x: A, y: A): Boolean = EqOrEquals[A].e(x, y)
}

trait PartialOrderOrEq[A] {
  def p(x: A, y: A)(implicit P: CouldHave[PartialOrder, A], E: EqOrEquals[A]): Double =
    CouldHave[PartialOrder, A].act(_.partialCompare(x, y))(() => if (EqOrEquals.e(x, y)) 0.0 else Double.NaN)
}
object PartialOrderOrEq {
  implicit def implicitPartialOrderOrEq[A: EqOrEquals](implicit P: CouldHave[PartialOrder, A]) = new PartialOrderOrEq[A] {}

  def apply[A: PartialOrderOrEq]: PartialOrderOrEq[A] = implicitly[PartialOrderOrEq[A]]

  def p[A: PartialOrderOrEq](x: A, y: A): Double = PartialOrderOrEq[A].p(x, y)
}

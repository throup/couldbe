package eu.throup
package couldbe
package testfixtures

import cats.*
import org.scalacheck.{Arbitrary, Cogen, Gen}

object CustomType {
  // Cheeky little way to use the same wildcard imports for
  // both Scala 2.13 and Scala 3, including all givens/implicits
  val `given` = "Some nonsense... can't believe this works!"

  trait SomethingToBe[A]
  object SomethingToBe {
    implicit def stb: SomethingToBe[BigInt] = new SomethingToBe[BigInt]() {}
  }

  // Define a type which has no inherent Order (or PartialOrder) to test our
  // permissive definition of PartialOrder.
  sealed trait NoOrder
  object NoOrder {
    case object A        extends NoOrder
    case object B        extends NoOrder
    case object Monkfish extends NoOrder
    case object Profit   extends NoOrder
  }
  implicit val hashNoOrder: Hash[NoOrder] = new Hash[NoOrder] {
    override def hash(x: NoOrder): Int                = x.hashCode()
    override def eqv(x: NoOrder, y: NoOrder): Boolean = x == y
  }
  implicit val arbNoOrder: Arbitrary[NoOrder] = Arbitrary {
    Gen.oneOf(NoOrder.A, NoOrder.B, NoOrder.Monkfish, NoOrder.Profit)
  }
  implicit val cogenNoOrder: Cogen[NoOrder] = Cogen[Int].contramap {
    case NoOrder.A        => 0
    case NoOrder.B        => 67
    case NoOrder.Monkfish => -245
    case NoOrder.Profit   => 42
  }

  type MonadStringFailure[F[_]] = MonadError[F, String]
  type MonadSilentFailure[F[_]] = MonadError[F, Unit]
  type EitherThrowable[A]       = Either[Throwable, A]
  type EitherString[A]          = Either[String, A]
  type EitherUnit[A]            = Either[Unit, A]
}

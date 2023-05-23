package eu.throup
package couldbe
package testfixtures

import cats.*
import org.scalacheck.{Arbitrary, Cogen, Gen}

object CustomType {
  trait SomethingToBe[A]
  object SomethingToBe {
    given SomethingToBe[BigInt] = new SomethingToBe[BigInt]() {}
  }

  // Define a type which has no inherent Order (or PartialOrder) to test our
  // permissive definition of PartialOrder.
  enum NoOrder {
    case A, B, Monkfish, Profit
  }
  given hashNoOrder: Hash[NoOrder] = new Hash[NoOrder] {
    override def hash(x: NoOrder): Int                = x.hashCode()
    override def eqv(x: NoOrder, y: NoOrder): Boolean = x == y
  }
  given arbNoOrder: Arbitrary[NoOrder] = Arbitrary {
    Gen.oneOf(NoOrder.A, NoOrder.B, NoOrder.Monkfish, NoOrder.Profit)
  }
  given cogenNoOrder: Cogen[NoOrder] = Cogen[Int].contramap {
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

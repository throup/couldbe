package eu.throup
package couldbe

import cats.{Eq, Hash, Semigroup}
import cats.kernel.laws.discipline.*
import cats.laws.discipline.*
import org.scalacheck.{Arbitrary, Cogen, Gen}
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.typelevel.discipline.scalatest.FunSuiteDiscipline

import scala.util.Try

class CouldBeGivenLawsSpec extends FunSuiteDiscipline with AnyFunSuiteLike with ScalaCheckPropertyChecks {
  checkAll("CouldBeGiven.Traverse", TraverseTests[CouldBeGiven].traverse[Int, String, BigDecimal, Long, Option, Option])
  checkAll("CouldBeGiven.CoflatMap", CoflatMapTests[CouldBeGiven].coflatMap[Int, String, Long])
  checkAll("CouldBeGiven.InvariantMonoidal", InvariantMonoidalTests[CouldBeGiven].invariantMonoidal[Int, String, Long])
  checkAll("CouldBeGiven.CommutativeMonad", CommutativeMonadTests[CouldBeGiven].commutativeMonad[Int, String, Long])
  checkAll("CouldBeGiven.MonadError", MonadErrorTests[CouldBeGiven, Unit].monadError[Int, String, Long])
  checkAll("IsGiven.NonEmptyTraverse", NonEmptyTraverseTests[IsGiven].nonEmptyTraverse[List, Int, BigDecimal, String, Long, Option, Option])
  checkAll("IsGiven.InvariantMonoidal", InvariantMonoidalTests[IsGiven].invariantMonoidal[Int, String, Long])
  checkAll("IsGiven.Bimonad", BimonadTests[IsGiven].bimonad[Int, String, Long])
  checkAll("IsGiven.CommutativeMonad", CommutativeMonadTests[IsGiven].commutativeMonad[Int, String, Long])

  checkAll("CouldBeGiven[String].Monoid", MonoidTests[CouldBeGiven[String]].monoid)
  checkAll("CouldBeGiven[String].Hash", HashTests[CouldBeGiven[String]].hash)
  checkAll("CouldBeGiven[String].PartialOrder", PartialOrderTests[CouldBeGiven[String]].partialOrder)

  checkAll("CouldBeGiven[Int].Monoid", MonoidTests[CouldBeGiven[Int]].monoid)
  checkAll("CouldBeGiven[Int].Hash", HashTests[CouldBeGiven[Int]].hash)
  checkAll("CouldBeGiven[Int].PartialOrder", PartialOrderTests[CouldBeGiven[Int]].partialOrder)

  checkAll("CouldBeGiven[List[Int]].Monoid", MonoidTests[CouldBeGiven[List[Int]]].monoid)
  checkAll("CouldBeGiven[List[Int]].Hash", HashTests[CouldBeGiven[List[Int]]].hash)
  checkAll("CouldBeGiven[List[Int]].PartialOrder", PartialOrderTests[CouldBeGiven[List[Int]]].partialOrder)

  // Define a type which has no inherent Order (or PartialOrder) to test our
  // permissive definition of PartialOrder.
  enum NoOrder {
    case A, B, Monkfish, Profit
  }
  given Hash[NoOrder] = new Hash[NoOrder]{
    override def hash(x: NoOrder): Int = x.hashCode()
    override def eqv(x: NoOrder, y: NoOrder): Boolean = x == y
  }
  given Arbitrary[NoOrder] = Arbitrary{ Gen.oneOf(NoOrder.A, NoOrder.B, NoOrder.Monkfish, NoOrder.Profit) }
  given Cogen[NoOrder] = Cogen[Int].contramap {
    case NoOrder.A => 0
    case NoOrder.B => 67
    case NoOrder.Monkfish => -245
    case NoOrder.Profit => 42
  }
  checkAll("CouldBeGiven[NoOrder].Hash", HashTests[CouldBeGiven[NoOrder]].hash)
  checkAll("CouldBeGiven[NoOrder].PartialOrder", PartialOrderTests[CouldBeGiven[NoOrder]].partialOrder)
}

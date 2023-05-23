package eu.throup
package couldbe

import org.scalacheck.{*, given}

package object testsupport {
  def genIsGiven[A: Arbitrary]: Gen[IsGiven[A]]                = Arbitrary.arbitrary[A].map(IsGiven(_))
  implicit def arbIsGiven[A: Arbitrary]: Arbitrary[IsGiven[A]] = Arbitrary(genIsGiven[A])

  def genCouldBeGiven[A: Arbitrary]: Gen[CouldBeGiven[A]] = Gen.oneOf(genIsGiven[A], Gen.const(IsNotGiven))
  implicit def arbCouldBeGiven[A: Arbitrary]: Arbitrary[CouldBeGiven[A]] = Arbitrary(genCouldBeGiven[A])

  implicit def cogenCouldBeGiven[A: Cogen]: Cogen[CouldBeGiven[A]] = Cogen[Option[A]].contramap(_.gift)
  implicit def cogenIsGiven[A: Cogen]: Cogen[IsGiven[A]]           = Cogen[CouldBeGiven[A]].contramap(identity)
}

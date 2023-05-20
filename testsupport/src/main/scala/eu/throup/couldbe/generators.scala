package eu.throup
package couldbe

import org.scalacheck.{*, given}

def genIsGiven[A: Arbitrary]: Gen[IsGiven[A]] = Arbitrary.arbitrary[A].map(IsGiven(_))
given [A: Arbitrary]: Arbitrary[IsGiven[A]]   = Arbitrary(genIsGiven)

def genCouldBeGiven[A: Arbitrary]: Gen[CouldBeGiven[A]] = Gen.oneOf(genIsGiven, Gen.const(IsNotGiven))
given [A: Arbitrary]: Arbitrary[CouldBeGiven[A]]        = Arbitrary(genCouldBeGiven)

given [A: Cogen]: Cogen[CouldBeGiven[A]] = Cogen[Option[A]].contramap(_.gift)
given [A: Cogen]: Cogen[IsGiven[A]]      = Cogen[CouldBeGiven[A]].contramap(identity)

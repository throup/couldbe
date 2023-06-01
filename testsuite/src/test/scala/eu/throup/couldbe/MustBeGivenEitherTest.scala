package eu.throup
package couldbe

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class MustBeGivenEitherTest extends AnyFreeSpec with Matchers {
  "Manually constructed" - {
    "IsGivenLeft[Int, String]" - {
      val ig = IsGivenLeft[Int, String](123)

      "identifies as left" in {
        ig.isLeft shouldBe true
        ig.isRight shouldBe false
      }

      "toLeft returns value" in {
        // lambda not used; no exception thrown
        val output = ig.toLeft(str => throw new Exception(str))
        output shouldBe 123
      }

      "toRight uses lambda to return value" in {
        val output = ig.toRight(int => s"Got this: $int")
        output shouldBe "Got this: 123"
      }

      "toEither returns Left of value" in {
        val output = ig.toEither
        output shouldBe Left(123)
      }
    }

    "IsGivenRight[Int, String]" - {
      val ig = IsGivenRight[Int, String]("barney")

      "identifies as right" in {
        ig.isLeft shouldBe false
        ig.isRight shouldBe true
      }

      "toLeft uses lambda to return value" in {
        val output = ig.toLeft(str => str.length)
        output shouldBe 6
      }

      "toRight returns value" in {
        // lambda not used; no exception thrown
        val output = ig.toRight(int => throw new Exception(s"$int boom!"))
        output shouldBe "barney"
      }

      "toEither returns Right of value" in {
        val output = ig.toEither
        output shouldBe Right("barney")
      }
    }
  }
}

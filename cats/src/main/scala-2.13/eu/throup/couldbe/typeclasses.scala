package eu.throup
package couldbe

import cats.*

object typeclasses {
  implicit def implicitEqCouldBeGiven[A: HashOrHashCode: PartialOrderOrEq]: EqCouldBeGiven[A] = new EqCouldBeGiven[A]
  implicit def implicitEqIsGiven[A: HashOrHashCode: PartialOrderOrEq]: EqIsGiven[A]           = new EqIsGiven[A]
  implicit def implicitShowCouldBeGiven[A: ShowOrToString]: ShowCouldBeGiven[A]               = new ShowCouldBeGiven[A]
  implicit def implicitMonoidCouldBeGiven[A: Semigroup]: MonoidCouldBeGiven[A] = new MonoidCouldBeGiven[A]
  implicit def implicitCouldBeGivenInstance: CouldBeGivenInstance              = new CouldBeGivenInstance
  implicit def implicitIsGivenInstance: IsGivenInstance                        = new IsGivenInstance
}

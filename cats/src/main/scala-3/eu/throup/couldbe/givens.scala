package eu.throup
package couldbe

import cats.*

// placeholder to provide compatibility with Scala 2.13 imports
object typeclasses {}

given [A: HashOrHashCode: PartialOrderOrEq]: EqCouldBeGiven[A] = new EqCouldBeGiven[A]
given [A: HashOrHashCode: PartialOrderOrEq]: EqIsGiven[A]      = new EqIsGiven[A]
given [A: ShowOrToString]: ShowCouldBeGiven[A]                 = new ShowCouldBeGiven[A]
given [A: Semigroup]: MonoidCouldBeGiven[A]                    = new MonoidCouldBeGiven[A]
given CouldBeGivenInstance                                     = new CouldBeGivenInstance
given IsGivenInstance                                          = new IsGivenInstance

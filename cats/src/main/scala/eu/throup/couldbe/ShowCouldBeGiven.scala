package eu.throup
package couldbe

import cats.Show

class ShowCouldBeGiven[A: ShowOrToString] extends Show[CouldBeGiven[A]] {
  override def show(t: CouldBeGiven[A]): String = t match {
    case IsGiven(a) => s"IsGiven(${ShowOrToString[A].s(a)})"
    case IsNotGiven => "IsNotGiven()"
  }
}

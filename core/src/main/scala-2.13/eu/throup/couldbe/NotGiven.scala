package eu.throup
package couldbe

// Shamelessly copied (and rewritten to Scala 2.13 syntax)
// from https://github.com/lampepfl/dotty/blob/3.2.2/library/src/scala/util/NotGiven.scala
// see that source for documentation and other comments.
// Replicated here to enable backporting CouldBeGiven to Scala 2.13.

final class NotGiven[+T] private ()

trait LowPriorityNotGiven {
  implicit def default[T]: NotGiven[T] = NotGiven.value
}

object NotGiven extends LowPriorityNotGiven {
  def value: NotGiven[Nothing] = new NotGiven[Nothing]()
  implicit def amb1[T](implicit ev: T): NotGiven[T] = ???
  implicit def amb2[T](implicit ev: T): NotGiven[T] = ???
}

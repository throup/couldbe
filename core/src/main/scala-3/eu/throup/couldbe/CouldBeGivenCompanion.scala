package eu.throup
package couldbe

private[couldbe] trait CouldBeGivenCompanion:
  given [A](using a: A): CouldBeGiven[A] = isGiven(a)

  given [A: NotGiven]: CouldBeGiven[A] = isNotGiven

  def apply[A](using go: CouldBeGiven[A]): CouldBeGiven[A] = go

  def act[A, B](f: A => B)(fallback: => B)(using go: CouldBeGiven[A]): B = go.act(f)(fallback)

  def isGiven[A](a: A): CouldBeGiven[A] = IsGiven(a)

  def isNotGiven[A]: CouldBeGiven[A] = IsNotGiven

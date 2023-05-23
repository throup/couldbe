package eu.throup
package couldbe

private[couldbe] trait CouldBeGivenCompanion {
  implicit def implicitIsGiven[A](implicit a: A): CouldBeGiven[A] = isGiven(a)

  implicit def implicitIsNotGiven[A](implicit x: NotGiven[A]): CouldBeGiven[A] = isNotGiven

  def apply[A](implicit go: CouldBeGiven[A]): CouldBeGiven[A] = go

  def act[A, B](f: A => B)(g: () => B)(implicit go: CouldBeGiven[A]): B = go.act(f)(g)

  def isGiven[A](a: A): CouldBeGiven[A] = IsGiven(a)

  def isNotGiven[A]: CouldBeGiven[A] = IsNotGiven
}

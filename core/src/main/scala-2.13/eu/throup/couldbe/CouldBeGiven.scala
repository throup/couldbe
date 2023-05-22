package eu.throup
package couldbe

trait CouldBeGiven[+A] {
  def isGiven: Boolean

  def gift: Option[A]

  def act[B](f: A => B)(g: () => B): B =
    gift match {
      case Some(a) => f(a)
      case None => g()
    }

  def map[B](f: A => B): CouldBeGiven[B]

  def flatMap[B](f: A => CouldBeGiven[B]): CouldBeGiven[B]
}

object CouldBeGiven {
  implicit def implicitIsGiven[A](implicit a: A): CouldBeGiven[A] = isGiven(a)

  implicit def implicitIsNotGiven[A](implicit x: NotGiven[A]): CouldBeGiven[A] = isNotGiven

  def apply[A](implicit go: CouldBeGiven[A]): CouldBeGiven[A] = go

  def act[A, B](f: A => B)(g: () => B)(implicit go: CouldBeGiven[A]): B = go.act(f)(g)

  def isGiven[A](a: A): CouldBeGiven[A] = IsGiven(a)

  def isNotGiven[A]: CouldBeGiven[A] = IsNotGiven
}

case class IsGiven[+A](get: A) extends CouldBeGiven[A] {
  override def isGiven: Boolean = true

  override def gift: Option[A] = Some(get)

  override def map[B](f: A => B): IsGiven[B] = IsGiven(f(get))

  override def flatMap[B](f: A => CouldBeGiven[B]): CouldBeGiven[B] = f(get)
}

case object IsNotGiven extends CouldBeGiven[Nothing] {
  override def isGiven: Boolean = false

  override def gift: Option[Nothing] = None

  override def map[B](f: Nothing => B): CouldBeGiven[B] = this

  override def flatMap[B](f: Nothing => CouldBeGiven[B]): CouldBeGiven[B] = this
}
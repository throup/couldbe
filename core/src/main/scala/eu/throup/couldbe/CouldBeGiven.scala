package eu.throup
package couldbe

sealed trait CouldBeGiven[+A] {
  def isGiven: Boolean

  def gift: Option[A]

  def or[B >: A](default: => B): B

  def act[B](f: A => B)(fallback: => B): B =
    gift match {
      case Some(a) => f(a)
      case None    => fallback
    }

  def map[B](f: A => B): CouldBeGiven[B]

  def flatMap[B](f: A => CouldBeGiven[B]): CouldBeGiven[B]
}
object CouldBeGiven extends CouldBeGivenCompanion

case class IsGiven[+A](get: A) extends CouldBeGiven[A] {
  override def isGiven: Boolean = true

  override def gift: Option[A] = Some(get)

  override def or[B >: A](default: => B): B = get

  override def map[B](f: A => B): IsGiven[B] = IsGiven(f(get))

  override def flatMap[B](f: A => CouldBeGiven[B]): CouldBeGiven[B] = f(get)
}

case object IsNotGiven extends CouldBeGiven[Nothing] {
  override def isGiven: Boolean = false

  override def gift: Option[Nothing] = None

  override def or[B](default: => B): B = default

  override def map[B](f: Nothing => B): CouldBeGiven[B] = this

  override def flatMap[B](f: Nothing => CouldBeGiven[B]): CouldBeGiven[B] = this
}

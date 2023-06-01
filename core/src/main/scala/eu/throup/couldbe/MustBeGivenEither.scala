package eu.throup
package couldbe

sealed trait MustBeGivenEither[+A, +B] {
  def isLeft: Boolean
  def isRight: Boolean = !isLeft

  def toLeft[C >: A](fromRight: B => C): C
  def toRight[C >: B](fromLeft: A => C): C

  def act[C](f: A => C)(g: B => C): C

  def toEither: Either[A, B] = act[Either[A, B]](Left[A, B])(Right[A, B])
}
object MustBeGivenEither extends MustBeGivenEitherCompanion

case class IsGivenLeft[+A, +B](get: A) extends MustBeGivenEither[A, B] {
  override def isLeft: Boolean = true

  override def toLeft[C >: A](fromRight: B => C): C = get

  override def toRight[C >: B](fromLeft: A => C): C = fromLeft(get)

  override def act[C](f: A => C)(g: B => C): C = f(get)
}

case class IsGivenRight[+A, +B](get: B) extends MustBeGivenEither[A, B] {
  override def isLeft: Boolean = false

  override def toLeft[C >: A](fromRight: B => C): C = fromRight(get)

  override def toRight[C >: B](fromLeft: A => C): C = get

  override def act[C](f: A => C)(g: B => C): C = g(get)
}

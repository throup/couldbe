package eu.throup
package couldbe

sealed trait MustBeGivenOneOf3[+A, +B, +C] {
  def _1[Z >: A](f: B => Z)(g: C => Z): Z

  def _2[Z >: B](f: A => Z)(g: C => Z): Z

  def _3[Z >: C](f: A => Z)(g: B => Z): Z

  def act[Z](f: A => Z)(g: B => Z)(h: C => Z): Z =
    this match {
      case IsGiven1Of3(a) => f(a)
      case IsGiven2Of3(b) => g(b)
      case IsGiven3Of3(c) => h(c)
    }
}
case class IsGiven1Of3[+A, +B, +C](a: A) extends MustBeGivenOneOf3[A, B, C] {
  override def _1[Z >: A](f: B => Z)(g: C => Z): Z = a

  override def _2[Z >: B](f: A => Z)(g: C => Z): Z = f(a)

  override def _3[Z >: C](f: A => Z)(g: B => Z): Z = f(a)
}

case class IsGiven2Of3[+A, +B, +C](b: B) extends MustBeGivenOneOf3[A, B, C] {
  override def _1[Z >: A](f: B => Z)(g: C => Z): Z = f(b)

  override def _2[Z >: B](f: A => Z)(g: C => Z): Z = b

  override def _3[Z >: C](f: A => Z)(g: B => Z): Z = g(b)
}

case class IsGiven3Of3[+A, +B, +C](c: C) extends MustBeGivenOneOf3[A, B, C] {
  override def _1[Z >: A](f: B => Z)(g: C => Z): Z = g(c)

  override def _2[Z >: B](f: A => Z)(g: C => Z): Z = g(c)

  override def _3[Z >: C](f: A => Z)(g: B => Z): Z = c
}

object MustBeGivenOneOf3 extends MustBeGivenOneOf3Companion

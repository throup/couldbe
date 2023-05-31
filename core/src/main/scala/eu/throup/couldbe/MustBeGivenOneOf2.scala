package eu.throup
package couldbe

sealed trait MustBeGivenOneOf2[+A, +B] {
  def _1[Z >: A](f: B => Z): Z

  def _2[Z >: B](f: A => Z): Z

}
case class IsGiven1Of2[+A, +B](a: A) extends MustBeGivenOneOf2[A, B] {
  override def _1[Z >: A](f: B => Z): Z = a

  override def _2[Z >: B](f: A => Z): Z = f(a)
}

case class IsGiven2Of2[+A, +B](b: B) extends MustBeGivenOneOf2[A, B] {
  override def _1[Z >: A](f: B => Z): Z = f(b)

  override def _2[Z >: B](f: A => Z): Z = b
}

object MustBeGivenOneOf2 extends MustBeGivenOneOf2Companion

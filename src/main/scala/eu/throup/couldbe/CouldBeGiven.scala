package eu.throup
package couldbe

import scala.util.NotGiven

sealed trait CouldBeGiven[A]:
  def isGiven: Boolean

  def gift: Option[A]

  def act[B](f: A => B)(g: () => B): B =
    gift match
      case Some(a) => f(a)
      case None    => g()

object CouldBeGiven:
  given [A](using a: A): CouldBeGiven[A]        = IsGiven(a)
  given [A](using NotGiven[A]): CouldBeGiven[A] = IsNotGiven()

  def apply[A](using go: CouldBeGiven[A]): CouldBeGiven[A]           = go
  def act[A, B](f: A => B)(g: () => B)(using go: CouldBeGiven[A]): B = go.act(f)(g)

case class IsGiven[A](get: A) extends CouldBeGiven[A]:
  override def isGiven: Boolean = true
  override def gift: Option[A]  = Some(get)

class IsNotGiven[A] extends CouldBeGiven[A]:
  override def isGiven: Boolean = false
  override def gift: Option[A]  = None

type CouldHave = [F[_[_]]] =>> [A[_]] =>> CouldBeGiven[F[A]]
object CouldHave:
  def apply[F[_[_]], A[_]](using gof: CouldBeGiven[F[A]]): CouldHave[F][A] = gof

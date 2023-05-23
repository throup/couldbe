package eu.throup
package couldbe

import cats.*

import scala.annotation.tailrec

class IsGivenInstance
    extends NonEmptyTraverse[IsGiven]
    with InvariantMonoidal[IsGiven]
    with Bimonad[IsGiven]
    with CommutativeMonad[IsGiven] {
  override def map[A, B](fa: IsGiven[A])(f: A => B): IsGiven[B] = fa.map(f)

  override def foldLeft[A, B](fa: IsGiven[A], b: B)(f: (B, A) => B): B = f(b, fa.get)

  override def foldRight[A, B](fa: IsGiven[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = f(fa.get, lb)

  override def nonEmptyTraverse[G[_]: Apply, A, B](fa: IsGiven[A])(f: A => G[B]): G[IsGiven[B]] =
    Apply[G].map(f(fa.get))(IsGiven(_))

  override def reduceLeftTo[A, B](fa: IsGiven[A])(f: A => B)(g: (B, A) => B): B = f(fa.get)

  override def reduceRightTo[A, B](fa: IsGiven[A])(f: A => B)(g: (A, Eval[B]) => Eval[B]): Eval[B] = Eval.now(f(fa.get))

  override def coflatMap[A, B](fa: IsGiven[A])(f: IsGiven[A] => B): IsGiven[B] = IsGiven(f(fa))

  override def extract[A](x: IsGiven[A]): A = x.get

  override def product[A, B](fa: IsGiven[A], fb: IsGiven[B]): IsGiven[(A, B)] = IsGiven((fa.get, fb.get))

  override def unit: IsGiven[Unit] = IsGiven(())

  override def ap[A, B](ff: IsGiven[A => B])(fa: IsGiven[A]): IsGiven[B] = IsGiven(ff.get(fa.get))

  override def flatMap[A, B](fa: IsGiven[A])(f: A => IsGiven[B]): IsGiven[B] = f(fa.get)

  override def tailRecM[A, B](a: A)(f: A => IsGiven[Either[A, B]]): IsGiven[B] = {
    @tailrec
    def theFunc(aa: A): IsGiven[B] = f(aa).get match {
      case Left(va)  => theFunc(va)
      case Right(vb) => IsGiven(vb)
    }

    theFunc(a)
  }

  override def pure[A](x: A): IsGiven[A] = IsGiven(x)
}

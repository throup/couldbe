package eu.throup
package couldbe

import cats.*

import scala.annotation.tailrec

class CouldBeGivenInstance
    extends Traverse[CouldBeGiven]
    with CoflatMap[CouldBeGiven]
    with InvariantMonoidal[CouldBeGiven]
    with CommutativeMonad[CouldBeGiven]
    with MonadError[CouldBeGiven, Unit] {
  override def map[A, B](fa: CouldBeGiven[A])(f: A => B): CouldBeGiven[B] = fa.map(f)

  override def foldLeft[A, B](fa: CouldBeGiven[A], b: B)(f: (B, A) => B): B = fa match {
    case IsGiven(a) => f(b, a)
    case IsNotGiven => b
  }

  override def foldRight[A, B](fa: CouldBeGiven[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = fa match {
    case IsGiven(a) => f(a, lb)
    case IsNotGiven => lb
  }

  override def traverse[G[_]: Applicative, A, B](fa: CouldBeGiven[A])(f: A => G[B]): G[CouldBeGiven[B]] = fa match {
    case IsGiven(a) => Applicative[G].map(f(a))(CouldBeGiven.isGiven)
    case IsNotGiven => Applicative[G].pure(CouldBeGiven.isNotGiven)
  }

  override def coflatMap[A, B](fa: CouldBeGiven[A])(f: CouldBeGiven[A] => B): CouldBeGiven[B] =
    CouldBeGiven.isGiven(f(fa))

  override def product[A, B](fa: CouldBeGiven[A], fb: CouldBeGiven[B]): CouldBeGiven[(A, B)] = (fa, fb) match {
    case (IsGiven(a), IsGiven(b)) => CouldBeGiven.isGiven((a, b))
    case _                        => CouldBeGiven.isNotGiven
  }

  override def unit: CouldBeGiven[Unit] = CouldBeGiven.isGiven(())

  override def ap[A, B](ff: CouldBeGiven[A => B])(fa: CouldBeGiven[A]): CouldBeGiven[B] = product(ff, fa).map {
    case (f, a) => f(a)
  }

  override def flatMap[A, B](fa: CouldBeGiven[A])(f: A => CouldBeGiven[B]): CouldBeGiven[B] = fa.flatMap(f)

  override def tailRecM[A, B](a: A)(f: A => CouldBeGiven[Either[A, B]]): CouldBeGiven[B] = {
    @tailrec
    def theFunc(aa: A): CouldBeGiven[B] = f(aa) match {
      case IsNotGiven         => CouldBeGiven.isNotGiven
      case IsGiven(Left(va))  => theFunc(va)
      case IsGiven(Right(vb)) => CouldBeGiven.isGiven(vb)
    }

    theFunc(a)
  }

  override def pure[A](x: A): CouldBeGiven[A] = CouldBeGiven.isGiven(x)

  override def raiseError[A](e: Unit): CouldBeGiven[A] = CouldBeGiven.isNotGiven

  override def handleErrorWith[A](fa: CouldBeGiven[A])(f: Unit => CouldBeGiven[A]): CouldBeGiven[A] = fa match {
    case g: IsGiven[_] => g
    case IsNotGiven    => f(())
  }
}

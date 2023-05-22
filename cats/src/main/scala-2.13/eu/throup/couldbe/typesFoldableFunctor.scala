package eu.throup
package couldbe

import cats.*

import scala.annotation.tailrec

class ShowCouldBeGiven[A: Show] extends Show[CouldBeGiven[A]] {
  override def show(t: CouldBeGiven[A]): String = t match {
    case IsGiven(a) => s"IsGiven(${Show[A].show(a)})"
    case IsNotGiven => "IsNotGiven()"
  }
}
object ShowCouldBeGiven {
  implicit def implicitShowCouldBeGiven[A: Show]: ShowCouldBeGiven[A] = new ShowCouldBeGiven[A]
}

class MonoidCouldBeGiven[A: Semigroup] extends Monoid[CouldBeGiven[A]] {
  override def combine(x: CouldBeGiven[A], y: CouldBeGiven[A]): CouldBeGiven[A] =
    (x, y) match {
      case (IsGiven(a), IsGiven(b)) => IsGiven(Semigroup[A].combine(a, b))
      case (_: IsGiven[?], IsNotGiven) => x
      case (IsNotGiven, _: IsGiven[?]) => y
      case _ => empty
    }

  override def empty: CouldBeGiven[A] = CouldBeGiven.isNotGiven
}
object MonoidCouldBeGiven {
  implicit def implicitMonoidCouldBeGiven[A: Semigroup]: MonoidCouldBeGiven[A] = new MonoidCouldBeGiven[A]
}

trait CouldBeGivenInstance extends Traverse[CouldBeGiven] with CoflatMap[CouldBeGiven] with InvariantMonoidal[CouldBeGiven] with CommutativeMonad[CouldBeGiven] with MonadError[CouldBeGiven, Unit] {
  override def map[A, B](fa: CouldBeGiven[A])(f: A => B): CouldBeGiven[B] = fa.map(f)

  override def foldLeft[A, B](fa: CouldBeGiven[A], b: B)(f: (B, A) => B): B = fa match {
    case IsGiven(a) => f(b, a)
    case IsNotGiven => b
  }

  override def foldRight[A, B](fa: CouldBeGiven[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = fa match {
    case IsGiven(a) => f(a, lb)
    case IsNotGiven => lb
  }

  override def traverse[G[_] : Applicative, A, B](fa: CouldBeGiven[A])(f: A => G[B]): G[CouldBeGiven[B]] = fa match {
    case IsGiven(a) => Applicative[G].map(f(a))(CouldBeGiven.isGiven)
    case IsNotGiven => Applicative[G].pure(CouldBeGiven.isNotGiven)
  }

  override def coflatMap[A, B](fa: CouldBeGiven[A])(f: CouldBeGiven[A] => B): CouldBeGiven[B] = CouldBeGiven.isGiven(f(fa))

  override def product[A, B](fa: CouldBeGiven[A], fb: CouldBeGiven[B]): CouldBeGiven[(A, B)] = (fa, fb) match {
    case (IsGiven(a), IsGiven(b)) => CouldBeGiven.isGiven((a, b))
    case _ => CouldBeGiven.isNotGiven
  }

  override def unit: CouldBeGiven[Unit] = CouldBeGiven.isGiven(())

  override def ap[A, B](ff: CouldBeGiven[A => B])(fa: CouldBeGiven[A]): CouldBeGiven[B] = product(ff, fa).map { case (f, a) => f(a) }

  override def flatMap[A, B](fa: CouldBeGiven[A])(f: A => CouldBeGiven[B]): CouldBeGiven[B] = fa.flatMap(f)

  override def tailRecM[A, B](a: A)(f: A => CouldBeGiven[Either[A, B]]): CouldBeGiven[B] = {
    @tailrec
    def theFunc(aa: A): CouldBeGiven[B] = f(aa) match {
      case IsNotGiven => CouldBeGiven.isNotGiven
      case IsGiven(Left(va)) => theFunc(va)
      case IsGiven(Right(vb)) => CouldBeGiven.isGiven(vb)
    }

    theFunc(a)
  }

  override def pure[A](x: A): CouldBeGiven[A] = CouldBeGiven.isGiven(x)

  override def raiseError[A](e: Unit): CouldBeGiven[A] = CouldBeGiven.isNotGiven

  override def handleErrorWith[A](fa: CouldBeGiven[A])(f: Unit => CouldBeGiven[A]): CouldBeGiven[A] = fa match {
    case g: IsGiven[_] => g
    case IsNotGiven => f(())
  }
}
object CouldBeGivenInstance {
  private val instance = new CouldBeGivenInstance {}
  implicit def implicitCouldBeGivenInstance: CouldBeGivenInstance = instance
}

trait IsGivenInstance extends NonEmptyTraverse[IsGiven] with InvariantMonoidal[IsGiven] with Bimonad[IsGiven] with CommutativeMonad[IsGiven] {
  override def map[A, B](fa: IsGiven[A])(f: A => B): IsGiven[B] = fa.map(f)

  override def foldLeft[A, B](fa: IsGiven[A], b: B)(f: (B, A) => B): B = f(b, fa.get)

  override def foldRight[A, B](fa: IsGiven[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = f(fa.get, lb)

  override def nonEmptyTraverse[G[_] : Apply, A, B](fa: IsGiven[A])(f: A => G[B]): G[IsGiven[B]] = Apply[G].map(f(fa.get))(IsGiven(_))

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
        case Left(va) => theFunc(va)
        case Right(vb) => IsGiven(vb)
      }

    theFunc(a)
  }

  override def pure[A](x: A): IsGiven[A] = IsGiven(x)
}
object IsGivenInstance {
  private val instance = new IsGivenInstance {}
  implicit def implicitIsGivenInstance: IsGivenInstance = instance
}

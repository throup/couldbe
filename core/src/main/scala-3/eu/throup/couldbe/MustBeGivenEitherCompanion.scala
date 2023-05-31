package eu.throup
package couldbe

private[couldbe] trait MustBeGivenEitherCompanion:
  // Be Right-biased -- if there is a B, we don't care whether there is an A
  given [A, B](using b: B): MustBeGivenEither[A, B] = isGivenRight(b)

  given [A, B: NotGiven](using a: A): MustBeGivenEither[A, B] = isGivenLeft(a)

  def apply[A, B](using ge: MustBeGivenEither[A, B]): MustBeGivenEither[A, B] = ge

  def act[A, B, C](f: A => C)(g: B => C)(using ge: MustBeGivenEither[A, B]): C = ge.act(f)(g)

  def isGivenLeft[A, B](a: A): MustBeGivenEither[A, B] = IsGivenLeft(a)

  def isGivenRight[A, B](b: B): MustBeGivenEither[A, B] = IsGivenRight(b)

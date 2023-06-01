package eu.throup
package couldbe

private[couldbe] trait MustBeGivenOneOf2Companion {
  def apply[A, B](implicit a: A): MustBeGivenOneOf2[A, B]           = IsGiven1Of2(a)
  def apply[A: NotGiven, B](implicit b: B): MustBeGivenOneOf2[A, B] = IsGiven2Of2(b)
}

package eu.throup
package couldbe

private[couldbe] trait MustBeGivenOneOf2Companion:
  def apply[A, B](using a: A): MustBeGivenOneOf2[A, B]           = IsGiven1Of2(a)
  def apply[A: NotGiven, B](using b: B): MustBeGivenOneOf2[A, B] = IsGiven2Of2(b)

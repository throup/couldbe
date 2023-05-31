package eu.throup
package couldbe

private[couldbe] trait MustBeGivenOneOf1Companion:
  def apply[A](using a: A): MustBeGivenOneOf1[A] = IsGiven1Of1(a)

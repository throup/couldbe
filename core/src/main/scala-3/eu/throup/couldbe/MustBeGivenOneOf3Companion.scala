package eu.throup
package couldbe

private[couldbe] trait MustBeGivenOneOf3Companion:
  given [A, B, C](using a: A): MustBeGivenOneOf3[A, B, C]                               = IsGiven1Of3(a)
  given [A: NotGiven, B, C](using b: B): MustBeGivenOneOf3[A, B, C]                     = IsGiven2Of3(b)
  given [A: NotGiven, B: NotGiven, C](using c: C): MustBeGivenOneOf3[A, B, C]           = IsGiven3Of3(c)
  def apply[A, B, C](using mbg: MustBeGivenOneOf3[A, B, C]): MustBeGivenOneOf3[A, B, C] = mbg

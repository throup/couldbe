package eu.throup
package couldbe

private[couldbe] trait MustBeGivenOneOf3Companion {
  implicit def implicitIsGiven1of3[A, B, C](implicit a: A): MustBeGivenOneOf3[A, B, C]           = IsGiven1Of3(a)
  implicit def implicitIsGiven2of3[A: NotGiven, B, C](implicit b: B): MustBeGivenOneOf3[A, B, C] = IsGiven2Of3(b)
  implicit def implicitIsGiven3of3[A: NotGiven, B: NotGiven, C](implicit c: C): MustBeGivenOneOf3[A, B, C] =
    IsGiven3Of3(c)
  def apply[A, B, C](implicit mbg: MustBeGivenOneOf3[A, B, C]): MustBeGivenOneOf3[A, B, C] = mbg
}

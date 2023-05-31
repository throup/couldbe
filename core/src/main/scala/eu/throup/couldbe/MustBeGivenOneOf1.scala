package eu.throup
package couldbe

sealed trait MustBeGivenOneOf1[+A] {
  def _1: A
}

case class IsGiven1Of1[+A](a: A) extends MustBeGivenOneOf1[A] {
  override def _1: A = a
}

object MustBeGivenOneOf1 extends MustBeGivenOneOf1Companion

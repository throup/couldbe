package eu.throup
package couldbe
package testfixtures

object GivenImplicit {
  // Don't do anything like this in production code... 👿
  implicit val string: String = "This string is given"
  implicit val impInt: Int    = 7
  implicit val impStr: String = "an example String"
}

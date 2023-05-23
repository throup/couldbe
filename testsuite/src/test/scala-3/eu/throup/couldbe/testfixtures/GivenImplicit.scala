package eu.throup
package couldbe
package testfixtures

object GivenImplicit {
  // Don't do anything like this in production code... 👿
  given string: String = "This string is given"
  given impInt: Int    = 7
  given impStr: String = "an example String"
}

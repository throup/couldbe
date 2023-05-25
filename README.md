# couldbe: optional Givens for Scala
[![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/throup/couldbe/scala.yml)](https://github.com/throup/couldbe/actions/workflows/scala.yml)
[![couldbe Scala version support](https://index.scala-lang.org/throup/couldbe/couldbe/latest-by-scala-version.svg?platform=jvm)](https://index.scala-lang.org/throup/couldbe/couldbe)
[![codecov](https://codecov.io/gh/throup/couldbe/branch/main/graph/badge.svg?token=XSUAQWYIOO)](https://codecov.io/gh/throup/couldbe)
[![javadoc](https://javadoc.io/badge2/eu.throup/couldbe_3/javadoc.svg)](https://javadoc.io/doc/eu.throup/couldbe)

![Cats Friendly Badge](https://typelevel.org/cats/img/cats-badge-tiny.png)

## Overview

**couldbe** is a small library, for the [Scala programming language](https://scala-lang.org), allowing you to refer to optional `given` instances (known as implicits in Scala 2).

Because `given` instances are resolved at compile time, they are either available or they are not. If your code requires a `given` instance, you add it to the function signature; if it doesn't, then you don't.

But what if your code _could_ use a `given` instance, but doesn't require one?  Maybe you are writing an algorithm which _could_ be simplified with evidence of a Monad, but doesn't actually require it? Or maybe you can provide a sensible default if the `given` value is not defined?

That's where **couldbe** can help you out.

### Quick example
(more examples later in the document)

```scala
// Function to return a Given string, if it is defined; or a default value otherwise.
def simpleGivenParameter(using message: CouldBeGiven[String]) =
  message match
    case IsGiven(actual) => actual
    case IsNotGiven      => "This is a default string"

// ---
// With no given String, the function returns the default value.
simpleGivenParameter == "This is a default string"

// ---
// With a given String, that is the value returned.
given String = "This string is given"
simpleGivenParameter == "This string is given"
```

Please note: it's not usually good practice to pass around something as generic as a `String` type in a `given` instance. This is a simple example to demonstrate the functionality.

## Getting started
To include **couldbe** in your project, add the appropriate dependencies to your `build.sbt`:
```sbt
libraryDependencies += "eu.throup" %% "couldbe" % "<version>"
```

The available packages are:
* `couldbe`: umbrella meta package to pull in `core` and `cats`
* `couldbe-core`: minimal implementation to allow basic functionality
* `couldbe-cats`: extra definitions and functionality for those using the [Cats](https://typelevel.org/cats/) library
* `couldbe-testsupport`: extra definitions and functionality to support writing tests


## Other examples
```
def yourFunction[A: CouldBeGiven, B: CouldHave[PartialOrder], F[_]: CouldBe[Monad]] =
  // Maybe there was a Given A... maybe there wasn't
  CouldBeGiven[A].act {
    // If there is one, do something with it.
    (a: A) => doSomethingWith(a)
  } {
    // Otherwise perform some fallback behaviour.
    () => doSomethingElseWithout()
  }
  
  // Maybe F is a Monad... maybe it isn't
  CouldBe[Monad, F].act {
    // If it is, do something monadic
    (monad: Monad[F]) => doSomethingMonadic(monad)
  } {
    // Otherwise perform some fallback behaviour.
    () => doSomethingUnmonadic()
  }
```

## Authors

* **Chris Throup** - [github](https://github.com/throup) - [linkedin](https://www.linkedin.com/in/christhroup)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

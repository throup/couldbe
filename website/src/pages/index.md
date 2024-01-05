---
title: optional Givens for Scala
---
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# couldbe: optional Givens for Scala
![couldbe social image](/img/couldbe-social-card.jpg)

[![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/throup/couldbe/scala.yml)](https://github.com/throup/couldbe/actions/workflows/scala.yml)
[![couldbe Scala version support](https://index.scala-lang.org/throup/couldbe/couldbe/latest-by-scala-version.svg?platform=jvm)](https://index.scala-lang.org/throup/couldbe/couldbe)
[![codecov](https://codecov.io/gh/throup/couldbe/branch/main/graph/badge.svg?token=XSUAQWYIOO)](https://codecov.io/gh/throup/couldbe)
[![javadoc](https://javadoc.io/badge2/eu.throup/couldbe_3/javadoc.svg)](https://javadoc.io/doc/eu.throup/couldbe)

![Cats Friendly Badge](https://typelevel.org/cats/img/cats-badge-tiny.png)
[![View us on GitHub](https://img.shields.io/badge/view%20us%20on-GitHub-lightgrey?style=for-the-badge&logo=github)](https://github.com/throup/couldbe)
## Overview

**couldbe** is a small library, for the [Scala programming language](https://scala-lang.org), allowing you to refer to optional `given` instances (known as implicits in Scala 2).

Because `given` instances are resolved at compile time, they are either available or they are not. If your code requires a `given` instance, you add it to the function signature; if it doesn't, then you don't.

But what if your code _could_ use a `given` instance, but doesn't require one?  Maybe you are writing an algorithm which _could_ be simplified with evidence of a Monad, but doesn't actually require it? Or maybe you can provide a sensible default if the `given` value is not defined?

That's where **couldbe** can help you out.

### Quick example
(more examples later in the document)


<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

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

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
// Function to return an implicit string, if it is defined; or a default value otherwise.
def simpleGivenParameter(implicit message: CouldBeGiven[String]) {
  message match {
    case IsGiven(actual) => actual
    case IsNotGiven      => "This is a default string"
  }
}

// ---
// With no implicit String, the function returns the default value.
simpleGivenParameter == "This is a default string"

// ---
// With an implicit String, that is the value returned.
implicit val expectThis: String = "This string is given"
simpleGivenParameter == "This string is given"
```

</TabItem>
</Tabs>

Please note: it's not usually good practice to pass around something as generic as a `String` type in a `given` instance. This is a simple example to demonstrate the functionality.

## Getting started
To include **couldbe** in your project, add the appropriate dependencies to your `build.sbt`:

```scala
libraryDependencies += "eu.throup" %% "couldbe" % "<version>"
```

The available packages are:

| Package               | Contains                                                                                            |
| --------------------- |---------------------------------------------------------------------------------------------------- |
| `couldbe`             | umbrella meta package to pull in `core` and `cats`                                                  |
| `couldbe-core`        | minimal implementation to allow basic functionality                                                 |
| `couldbe-cats`        | extra definitions and functionality for those using the [Cats](https://typelevel.org/cats/) library |
| `couldbe-testsupport` | extra definitions and functionality to support writing tests                                        |

## Other examples

<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

```scala
def yourFunction[A: CouldBeGiven, B: CouldHave[PartialOrder], F[_]: CouldBe[Monad]] =
  // Maybe there was a Given A... maybe there wasn't
  CouldBeGiven[A].act {
    // If there is one, do something with it.
    (a: A) => doSomethingWith(a)
  } {
    // Otherwise perform some fallback behaviour.
    doSomethingElseWithout()
  }
  
  // Maybe F is a Monad... maybe it isn't
  CouldBe[Monad, F].act {
    // If it is, do something monadic
    (monad: Monad[F]) => doSomethingMonadic(monad)
  } {
    // Otherwise perform some fallback behaviour.
    doSomethingUnmonadic()
  }
```

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
def yourFunction[A: CouldBeGiven, B, F[_]](
        implicit B: CouldHave[PartialOrder, B],
                 F: CouldBe[Monad, F]) = {
  // Maybe there was a Given A... maybe there wasn't
  CouldBeGiven[A].act {
    // If there is one, do something with it.
    (a: A) => doSomethingWith(a)
  } {
    // Otherwise perform some fallback behaviour.
    doSomethingElseWithout()
  }

  // Maybe F is a Monad... maybe it isn't
  CouldBe[Monad, F].act {
    // If it is, do something monadic
    (monad: Monad[F]) => doSomethingMonadic(monad)
  } {
    // Otherwise perform some fallback behaviour.
    doSomethingUnmonadic()
  }
}
```

</TabItem>
</Tabs>

## Authors

* **Chris Throup** - [github](https://github.com/throup) - [linkedin](https://www.linkedin.com/in/christhroup)

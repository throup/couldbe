# couldbe: optional Givens for Scala
[![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/throup/couldbe/scala.yml)](https://github.com/throup/couldbe/actions/workflows/scala.yml)

**couldbe** is a Scala library for specifying optional Given instances (previously known as implicits).

A quick example:
```
def yourFunction[A: CouldBeGiven, F[_]: CouldHave[Monad]] =
  // Maybe there was a Given A... maybe there wasn't
  CouldBeGiven[A].act {
    // If there is one, do something with it.
    (a: A) => doSomethingWith(a)
  } {
    // Otherwise perform some fallback behaviour.
    () => doSomethingElseWithout()
  }
  
  // Maybe F is a Monad... maybe it isn't
  CouldHave[Monad, F].act {
    // If it is, do something monadic
    (monad: Monad[F]) => doSomethingMonadic(monad)
  } {
    // Otherwise perform some fallback behaviour.
    () => doSomethingUnmonadic()
  }
```

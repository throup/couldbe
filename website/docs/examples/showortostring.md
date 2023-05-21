---
sidebar_position: 1
---

# ShowOrToString

If you are using the Cats library, you can define a `Show` type class for any data type.

```scala
// Simplified definition.
// See https://typelevel.org/cats/typeclasses/show.html for further details
trait Show[T]:
  def show(t: T): String
```

The `Show` type provides a String representation of an object, guaranteed to be a deliberate choice instead of the default `toString()` defined on every JVM object.

Sounds great! We should use it... if it is defined.

But maybe this is not _critical_ to our logic. Maybe we are logging, within a greater algortithm, and which to use the `Show` representation when it is defined. But if it isn't, we can accept falling back to the default `toString()` method.

```scala
// With Show
log.info("We diddled the doodle using " + Show.show(someObject))

// Without Show
log.info("We diddled the doodle using " + someObject.toString)
```

This sounds like an optional `given`!

```scala
if (CouldBeGiven[Show[SomeObject]].isGiven) {
  // use Show
} else {
  // use toString
}
```

In fact, the `couldbe-cats` package provides a ready implementation of `ShowOrToString`:
```scala
trait ShowOrToString[-A: CouldHave[Show]]:
  def s(a: A): String = CouldHave[Show, A].act(_.show(a))(() => a.toString)
```

which allows you to simplify the logic to:
```scala
def yourFunction[A: ShowOrToString](a: A):
  // ...
  log.info("We diddled the doodle using " + ShowOrToString.s(someObject))
  // ...
```

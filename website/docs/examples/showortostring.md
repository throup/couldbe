---
sidebar_position: 1
---
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# ShowOrToString

If you are using the Cats library, you can define a `Show` type class for any data type.

<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

```scala
// Simplified definition.
// See https://typelevel.org/cats/typeclasses/show.html for further details
trait Show[T]:
  def show(t: T): String
```

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
// Simplified definition.
// See https://typelevel.org/cats/typeclasses/show.html for further details
trait Show[T] {
  def show(t: T): String
}
```

</TabItem>
</Tabs>

The `Show` type provides a String representation of an object, guaranteed to be a deliberate choice instead of the default `toString()` defined on every JVM object.

Sounds great! We should use it... if it is defined.

But maybe this is not _critical_ to our logic. Maybe we are logging, within a greater algortithm, and which to use the `Show` representation when it is defined. But if it isn't, we can accept falling back to the default `toString()` method.

<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

```scala
// With Show
log.info("We diddled the doodle using " + Show.show(someObject))

// Without Show
log.info("We diddled the doodle using " + someObject.toString)
```

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
// With Show
log.info("We diddled the doodle using " + Show.show(someObject))

// Without Show
log.info("We diddled the doodle using " + someObject.toString)
```

</TabItem>
</Tabs>

This sounds like an optional `given`!

<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

```scala
if (CouldBeGiven[Show[SomeObject]].isGiven) {
  // use Show
} else {
  // use toString
}
```

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
if (CouldBeGiven[Show[SomeObject]].isGiven) {
  // use Show
} else {
  // use toString
}
```

</TabItem>
</Tabs>

In fact, the `couldbe-cats` package provides a ready implementation of `ShowOrToString`:

<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

```scala
trait ShowOrToString[-A: CouldHave[Show]]:
  def s(a: A): String = CouldHave[Show, A].act(_.show(a))(a.toString)
```

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
trait ShowOrToString[-A](implicit A: CouldHave[Show, A]) {
  def s(a: A): String = CouldHave[Show, A].act(_.show(a))(a.toString)
}
```

</TabItem>
</Tabs>

which allows you to simplify the logic to:

<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

```scala
def yourFunction[A: ShowOrToString](a: A):
  // ...
  log.info("We diddled the doodle using " + ShowOrToString.s(someObject))
  // ...
```

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
def yourFunction[A: ShowOrToString](a: A) {
  // ...
  log.info("We diddled the doodle using " + ShowOrToString.s(someObject))
  // ...
}
```

</TabItem>
</Tabs>

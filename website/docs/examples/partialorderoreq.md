---
sidebar_position: 2
---
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# PartialOrderOrEq

If you are using the Cats library, you can define a `PartialOrder` type class for any data type.

<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

```scala
// Simplified definition.
// See https://typelevel.org/cats/api/cats/kernel/PartialOrder.html for further details
trait PartialOrder[A]:
  def partialCompare(x: A, y: A): Double
```

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
// Simplified definition.
// See https://typelevel.org/cats/api/cats/kernel/PartialOrder.html for further details
trait PartialOrder[A] {
  def partialCompare(x: A, y: A): Double
}
```

</TabItem>
</Tabs>

The `PartialOrder` type provides a method for comparing two objects of the given type, returning a double value of:
- 0.0 if the two objects are equivalent
- -1.0 if `x < y`
- 1.0 if `x < y`
- `NaN` if `x` and `y` cannot be compared.

Cats is also kind enough to provide implicit `<`, `<=`, `===`, `>=` and `>` operators if the given type does not provide them already.

That's all nice, but not every type comes with a given `PartialOrder` for us to use.

But logically, it must be possible to define a `PartialOrder` for _every_ type; even if it is _very partial!_

The `couldbe-cats` package provides a ready implementation of `PartialOrderOrEq`:

<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

```scala
trait PartialOrderOrEq[A: CouldHave[PartialOrder]: EqOrEquals]:
  def p(x: A, y: A): Double =
    CouldHave[PartialOrder, A]
      .act(_.partialCompare(x, y))
          (() => if EqOrEquals.e(x, y) then 0.0 else Double.NaN)
```

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
trait PartialOrderOrEq[A: EqOrEquals](A: CouldHave[PartialOrder, A]) {
  def p(x: A, y: A): Double =
    CouldHave[PartialOrder, A]
      .act(_.partialCompare(x, y))
          (() => if (EqOrEquals.e(x, y)) 0.0 else Double.NaN)
}
```

</TabItem>
</Tabs>

This is a "best attempt" at a partial order for any given type.

In the following function

<Tabs groupId="dialect">
<TabItem value="scala3" label="Scala 3">

```scala
def yourFunction[A: PartialOrderOrEq](x: A, y: A):
  // ...
  PartialOrderOrEq.p(x, y)
  // ...
```

</TabItem>
<TabItem value="scala2" label="Scala 2.13">

```scala
def yourFunction[A: PartialOrderOrEq](x: A, y: A) {
  // ...
  PartialOrderOrEq.p(x, y)
  // ...
}
```

</TabItem>
</Tabs>

the following rules will be applied:
1. if a given `PartialOrder` (or a complete `Order`, which extends the trait) exists, then that instance will be used to perform the comparison
2. otherwise, if a given `Eq` (Cats Equality) instance exists, then the function will return 0.0 if `x === y`; `NaN` otherwise
3. finally, if we still have no comparison, the two values will be compared using the JVM `.equals()` method; returning 0.0 if `x.equals(y)` or `NaN` otherwise.

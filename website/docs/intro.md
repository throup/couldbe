---
sidebar_position: 1
---

# Getting started

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

---
sidebar_position: 1
---

# Getting started

Packages for **couldbe** are published to [Github's maven registry](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry). To include in your project, add the appropriate dependencies to your `build.sbt`:
```sbt
libraryDependencies += "eu.throup" %% "couldbe" % "<latest version>"
```

The available packages are:
* `couldbe`: umbrella meta package to pull in `core` and `cats`
* `couldbe-core`: minimal implementation to allow basic functionality
* `couldbe-cats`: extra definitions and functionality for those using the [Cats](https://typelevel.org/cats/) library
* `couldbe-testsupport`: extra definitions and functionality to support writing tests

You will also need to configure access to [Github's package registry](https://docs.github.com/en/packages/learn-github-packages/introduction-to-github-packages). There are many ways to do this, but the simplest is to add [sbt-github-packages](https://github.com/djspiewak/sbt-github-packages) to your `project/plugin.sbt`:
```sbt
addSbtPlugin("com.codecommit" % "sbt-github-packages" % "0.5.3")
```

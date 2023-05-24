ThisBuild / scalaVersion  := "3.2.2"
ThisBuild / organization  := "eu.throup"
ThisBuild / versionScheme := Some("early-semver")

import xerial.sbt.Sonatype.*
sonatypeProjectHosting             := Some(GitHubHosting("throup", "couldbe", "chris@throup.eu"))
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"
sonatypeRepository                 := "https://s01.oss.sonatype.org/service/local"

import ReleaseTransformations.*
releaseCrossBuild := true
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("+publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)

lazy val overrides = Seq(
  // Plugin versions; update these in project/plugins.sbt as well
  "com.github.sbt" % "sbt-release_2.12_1.0"    % Versions.Plugin.sbtRelease,
  "org.scoverage"  % "sbt-scoverage_2.12_1.0"  % Versions.Plugin.sbtCoverage,
  "org.scalameta"  % "sbt-mdoc_2.12_1.0"       % Versions.Plugin.sbtMdoc,
  "org.scalameta"  % "sbt-scalafmt_2.12_1.0"   % Versions.Plugin.scalaFmt,
  "com.github.sbt" % "sbt-ci-release_2.12_1.0" % Versions.Plugin.sbtCiRelease,
// Transitive dependencies; versions specified to avoid known vulnerabilities
  "com.google.protobuf"        % "protobuf-java"    % Versions.Override.protobufJava,
  "com.fasterxml.jackson.core" % "jackson-databind" % Versions.Override.jacksonDatabind,
  "org.jsoup"                  % "jsoup"            % Versions.Override.jsoup,
  "org.yaml"                   % "snakeyaml"        % Versions.Override.snakeyaml,
  "org.apache.pdfbox"          % "pdfbox"           % Versions.Override.pdfbox,
  "io.undertow"                % "undertow-core"    % Versions.Override.undertow
)

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-encoding",
    "UTF-8",
    "-feature",
    "-Xfatal-warnings",
    "-deprecation"
  ) ++
    (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) =>
        Seq(
          "-source:3.2",
          "-explain",
          "-explain-types"
        )
      case _ =>
        Seq(
          "-Xsource:3.2",
          "-Wunused:imports,privates,locals",
          "-Wvalue-discard"
        )
    }),
  dependencyOverrides ++= overrides,
  libraryDependencies ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => Seq()
      case Some((2, _)) =>
        Seq(
          compilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full)
        )
    }
  },
  licenses := Seq("MIT" -> url("https://opensource.org/license/mit/")),
  description := "A small library, for the Scala programming language, allowing you to refer to optional given instances (previously known as implicits).",
  developers := List (
    Developer(
      id = "throup",
      name = "Chris Throup",
      email = "chris@throup.eu",
      url = url("https://github.com/throup")
    )
  ),
  homepage := Some(url("https://github.com/throup/couldbe"))
)

lazy val crossScala = Seq(crossScalaVersions := Seq("2.13.10", "3.2.2"))

lazy val root = (project in file("."))
  .settings(name := "couldbe")
  .settings(commonSettings)
  .settings(crossScala)
  .aggregate(core, cats, testsupport, testsuite)
  .dependsOn(core, cats)

lazy val core = project
  .settings(name := "couldbe-core")
  .settings(commonSettings)
  .settings(crossScala)

lazy val cats = project
  .settings(name := "couldbe-cats")
  .settings(commonSettings)
  .settings(crossScala)
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % Versions.cats
    )
  )
  .dependsOn(core)

lazy val testsupport = project
  .settings(name := "couldbe-testsupport")
  .settings(commonSettings)
  .settings(crossScala)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatestplus" %% "scalacheck-1-16" % Versions.scalatestPlus
    )
  )
  .dependsOn(core, cats)

lazy val testsuite = project
  .settings(name := "couldbe-testsupport")
  .settings(commonSettings)
  .settings(crossScala)
  .settings(
    publishArtifact := false,
    publish / skip  := true
  )
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest"              % Versions.scalatest,
      "org.typelevel" %% "cats-laws"              % Versions.cats,
      "org.typelevel" %% "cats-testkit-scalatest" % Versions.catsTestkit
    ).map(_ % Test)
  )
  .dependsOn(core, cats, testsupport)

lazy val docs = (project in file("couldbe-docs")) // important: it must not be docs/
  .settings(
    moduleName := "couldbe-docs",
    mdocVariables := Map(
      "VERSION" -> version.value
    )
  )
  .settings(commonSettings)
  .dependsOn(core, cats)
  .enablePlugins(MdocPlugin, DocusaurusPlugin)

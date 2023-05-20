ThisBuild / scalaVersion := "3.2.2"
ThisBuild / organization := "eu.throup"
ThisBuild / githubOwner := "throup"
ThisBuild / githubRepository := "couldbe"

lazy val overrides = Seq(
  // Plugin versions; update these in project/plugins.sbt as well
  "com.codecommit" % "sbt-github-packages" % Versions.Plugin.sbtGithubPackages,
  "com.codecommit" % "sbt-github-packages_2.12_1.0" % Versions.Plugin.sbtGithubPackages,
  "com.github.sbt" % "sbt-release" % Versions.Plugin.sbtRelease,
  "com.github.sbt" % "sbt-release_2.12_1.0" % Versions.Plugin.sbtRelease,
  // Transitive dependencies; versions specified to avoid known vulnerabilities
  "com.google.protobuf" % "protobuf-java" % Versions.Override.protobufJava,
  "com.fasterxml.jackson.core" % "jackson-databind" % Versions.Override.jacksonDatabind,
  "org.jsoup" % "jsoup" % Versions.Override.jsoup,
  "org.yaml" % "snakeyaml" % Versions.Override.snakeyaml
)

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-encoding", "UTF-8",
    "-feature",
    "-Xfatal-warnings",
    "-deprecation",
    "-source:3.2",
    "-explain",
    "-explain-types"
  ),
  dependencyOverrides ++= overrides,
  githubOwner      := "throup",
  githubRepository := "couldbe"
)

lazy val root = (project in file("."))
  .settings(name := "couldbe")
  .aggregate(core, cats, testsupport, testsuite)
  .dependsOn(core, cats)

lazy val core = project
  .settings(name := "couldbe-core")
  .settings(commonSettings)

lazy val cats = project
  .settings(name := "couldbe-cats")
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel"              %% "cats-core"     % Versions.cats,
    ),
  )
  .dependsOn(core)

lazy val testsupport = project
  .settings(name := "couldbe-testsupport")
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatestplus" %% "scalacheck-1-16"        % Versions.scalatestPlus,
    ),
  )
  .dependsOn(core, cats)

lazy val testsuite = project
  .settings(name := "couldbe-testsupport")
  .settings(commonSettings)
  .settings(
    publishArtifact := false,
    publish / skip := true
  )
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest"     %% "scalatest"              % Versions.scalatest,
      "org.typelevel"     %% "cats-laws"              % Versions.cats,
      "org.typelevel"     %% "cats-testkit-scalatest" % Versions.catsTestkit,
    ).map(_ % Test),
  )
  .dependsOn(core, cats, testsupport)

ThisBuild / scalaVersion := "3.2.2"

scalacOptions ++= {
  Seq(
    "-encoding", "UTF-8",
    "-feature",
    "-Xfatal-warnings",
    "-deprecation",
    "-source:3.2",
    "-explain",
    "-explain-types",
  )
}

githubOwner := "throup"
githubRepository := "couldbe"

lazy val root = (project in file("."))
  .settings(
    organization := "eu.throup",
    name := "couldbe"
  )
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging,
      "org.scalactic" %% "scalactic" % Versions.scalatest,
      "org.typelevel" %% "cats-core" % Versions.cats,
    ),
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % Versions.logback,
      "io.chrisdavenport" %% "cats-scalacheck" % Versions.catsScalacheck,
      "org.scalatest" %% "scalatest" % Versions.scalatest,
      "org.scalatestplus" %% "scalacheck-1-16" % Versions.scalatestPlus,
      "org.typelevel" %% "cats-laws" % Versions.cats,
      "org.typelevel" %% "cats-testkit-scalatest" % Versions.catsTestkit,
      "org.typelevel" %% "discipline-scalatest" % Versions.disciplineScalatest
    ).map(_ % Test),
    dependencyOverrides ++= Seq(
      // Plugin versions; update these in project/plugins.sbt as well
      "com.codecommit" % "sbt-github-packages" % Versions.Plugin.sbtGithubPackages,
      "com.github.sbt" % "sbt-release" % Versions.Plugin.sbtRelease,
      // Transitive dependencies; versions specified to avoid known vulnerabilities
      "com.google.protobuf" % "protobuf-java" % Versions.Override.protobufJava,
      "com.fasterxml.jackson.core" % "jackson-databind" % Versions.Override.jacksonDatabind,
      "org.jsoup" % "jsoup" % Versions.Override.jsoup,
      "org.yaml" % "snakeyaml" % Versions.Override.snakeyaml,
    )
  )

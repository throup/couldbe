ThisBuild / scalaVersion := "3.2.2"
scalacOptions += "-feature"
scalacOptions += "-explain"

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
    ).map(_ % Test)
  )

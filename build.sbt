ThisBuild / scalaVersion     := "3.2.2"
ThisBuild / organization     := "eu.throup"
ThisBuild / githubOwner      := "throup"
ThisBuild / githubRepository := "couldbe"
ThisBuild / versionScheme    := Some("early-semver")

lazy val overrides = Seq(
  // Plugin versions; update these in project/plugins.sbt as well
  "com.codecommit" % "sbt-github-packages"          % Versions.Plugin.sbtGithubPackages,
  "com.codecommit" % "sbt-github-packages_2.12_1.0" % Versions.Plugin.sbtGithubPackages,
  "com.github.sbt" % "sbt-release"                  % Versions.Plugin.sbtRelease,
  "com.github.sbt" % "sbt-release_2.12_1.0"         % Versions.Plugin.sbtRelease,
  "org.scoverage"  % "sbt-scoverage"                % Versions.Plugin.sbtCoverage,
  "org.scoverage"  % "sbt-scoverage_2.12_1.0"       % Versions.Plugin.sbtCoverage,
  "org.scalameta"  % "sbt-mdoc"                     % Versions.Plugin.sbtMdoc,
  "org.scalameta"  % "sbt-mdoc_2.12_1.0"            % Versions.Plugin.sbtMdoc,
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
    "-deprecation",
  ) ++
    (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => Seq(
        "-source:3.2",
        "-explain",
        "-explain-types",
      )
      case _ => Seq(
        "-Xsource:3.2",
        "-Wunused:imports,privates,locals",
        "-Wvalue-discard",
      )
    }),
  dependencyOverrides ++= overrides,
  githubOwner      := "throup",
  githubRepository := "couldbe",
  libraryDependencies ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => Seq()
      case Some((2, _)) => Seq(
        compilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full)
      )
    }
  }
)

lazy val crossScala = Seq(crossScalaVersions := Seq("2.13.10", "3.2.2"))

lazy val root = (project in file("."))
  .settings(name := "couldbe")
  .settings(commonSettings)
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
      "VERSION"                  -> version.value,
      "VERSIONsbtgithubpackages" -> Versions.Plugin.sbtGithubPackages
    )
  )
  .settings(commonSettings)
  .dependsOn(core, cats)
  .enablePlugins(MdocPlugin, DocusaurusPlugin)

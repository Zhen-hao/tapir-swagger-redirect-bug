ThisBuild / version := "0.0.1-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name                                   := "tapir-swagger-iu",
    dependencyCheckOSSIndexAnalyzerEnabled := Some(false),
    dependencyCheckAssemblyAnalyzerEnabled := Some(false),
    dependencyCheckOutputDirectory         := Some(file(".")),
    dependencyCheckFormats                 := Seq("HTML", "JSON"),
    coverageDataDir                        := target.value
  )

lazy val finatraVersion = "22.2.0"
lazy val tapirVersion   = "1.0.0-RC1"

scalacOptions ++= Seq(
  "-Ymacro-annotations",
  "-encoding",
  "utf8",
  "-deprecation",
  "-unchecked",
  "-language:higherKinds,existentials",
  "-language:postfixOps",
  "-Wunused:imports,linted,privates,locals,explicits,params"
)

scalafmtOnCompile := true

testOptions += Tests.Setup(_ => sys.props("testing") = "true")

libraryDependencies ++= Seq(
  "com.typesafe"                 % "config"                  % "1.4.2",
  "com.twitter"                 %% "finatra-http-server"     % finatraVersion,
  "ch.qos.logback"               % "logback-core"            % "1.2.10",
  "ch.qos.logback"               % "logback-classic"         % "1.2.10",
  "com.typesafe.scala-logging"  %% "scala-logging"           % "3.9.4",
  "org.scalactic"               %% "scalactic"               % "3.2.11",
  "com.softwaremill.sttp.tapir" %% "tapir-core"              % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-finatra-server"    % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-json-circe"        % tapirVersion,
  "org.scalatest"               %% "scalatest"               % "3.2.11"       % Test,
  "com.twitter"                 %% "finatra-http-server"     % finatraVersion % Test classifier "tests",
  "com.twitter"                 %% "inject-server"           % finatraVersion % Test classifier "tests",
  "com.twitter"                 %% "inject-app"              % finatraVersion % Test classifier "tests",
  "com.twitter"                 %% "inject-core"             % finatraVersion % Test classifier "tests",
  "com.twitter"                 %% "inject-modules"          % finatraVersion % Test classifier "tests",
)

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", "maven", "org.webjars", "swagger-ui", "pom.properties") =>
    MergeStrategy.singleOrError
  case PathList("META-INF", "resources", "webjars", "swagger-ui", xs @ _*) =>
    MergeStrategy.first
  case PathList("META-INF", xs @ _*)       => MergeStrategy.discard
  case _                                   => MergeStrategy.first
}

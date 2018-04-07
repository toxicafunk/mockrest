organization := "es.richweb"
name := "mockrest"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.4"

val Http4sVersion = "0.18.7"
val Specs2Version = "4.0.0"
val LogbackVersion = "1.2.3"
val CirceVersion = "0.9.3"

libraryDependencies ++= Seq(
  "org.http4s"     %% "http4s-blaze-server"  % Http4sVersion,
  "org.http4s"     %% "http4s-circe"         % Http4sVersion,
  "org.http4s"     %% "http4s-dsl"           % Http4sVersion,
  "org.specs2"     %% "specs2-core"          % Specs2Version % "test",
  "ch.qos.logback" %  "logback-classic"      % LogbackVersion
)

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-circe" % Http4sVersion,
  // Optional for auto-derivation of JSON codecs
  "io.circe" %% "circe-generic" % CirceVersion,
  // Optional for string interpolation to JSON model
  "io.circe" %% "circe-literal" % CirceVersion
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

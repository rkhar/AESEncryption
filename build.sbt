
name := "aesencryption"
version := "1.0.0"
scalaVersion := "2.12.7"

lazy val akkaVersion              = "2.5.22"
lazy val akkaHttpVersion          = "10.1.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka"  %% "akka-http"                 % akkaHttpVersion,
  "commons-io" % "commons-io" % "2.4",
  "com.typesafe.akka"  %% "akka-http-spray-json"      % akkaHttpVersion,
  "com.typesafe.akka"  %% "akka-http-xml"             % akkaHttpVersion,
  "com.typesafe.akka"  %% "akka-stream"               % akkaVersion,
  "de.heikoseeberger"  %% "akka-http-json4s"          % "1.20.1",
  "com.typesafe"       % "config"                     % "1.3.3",
  "com.typesafe.akka"  %% "akka-http-testkit"         % akkaHttpVersion % Test,
  "com.typesafe.akka"  %% "akka-testkit"              % akkaVersion % Test,
  "com.typesafe.akka"  %% "akka-stream-testkit"       % akkaVersion % Test,
  "org.scalatest"      %% "scalatest"                 % "3.0.1" % Test
)

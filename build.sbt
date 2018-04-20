name := "chain"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= {
  val akkaV = "2.5.11"
  val akkaHttpV = "10.0.12"
  val phantomV = "2.14.5"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "org.json4s" % "json4s-jackson_2.12" % "3.6.0-M2"
  )
}
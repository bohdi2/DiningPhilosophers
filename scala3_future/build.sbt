val scala3Version = "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3_future",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    scalacOptions ++= Seq(
      "-language:postfixOps"
    ),

    libraryDependencies += "org.scalatest" %% "scalatest-funsuite" % "3.2.10" % "test"
)

name := "spark-testing"

version := "1.0"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "2.2.1",
  "org.apache.spark" %% "spark-sql" % "2.2.1",
  "org.apache.spark" %% "spark-streaming" % "2.2.1",
  "org.apache.spark" %% "spark-streaming" % "2.2.1",
"org.scalatest" %% "scalatest" % "3.0.5" % Test
)

parallelExecution in ThisBuild := false
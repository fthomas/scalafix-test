name := "scalafix-test"

libraryDependencies := List(
  //"co.fs2" %% "fs2-core" % "0.10.7",
  //"org.http4s" %% "http4s-core" % "0.18.24",
  //"org.http4s" %% "http4s-dsl" % "0.18.24",
  "org.typelevel" %% "cats-core" % "2.4.2"
  //"com.spotify" %% "scio-avro" % "0.6.1",
  //"com.spotify" %% "scio-core" % "0.6.1",
  //"com.spotify" %% "scio-test" % "0.6.1",
  //"com.spotify" %% "scio-bigquery" % "0.6.1",
  //"org.scalatest" %% "scalatest" % "3.2.2"
)

scalafixDependencies += "com.nequissimus" %% "sort-imports" % "0.5.5"

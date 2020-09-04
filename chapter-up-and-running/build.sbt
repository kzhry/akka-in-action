// enablePlugins(JavaServerAppPackaging) // Heroku へのデプロイに必要らしい

name := "goticks"

version := "1.0"

organization := "com.goticks" // アプリケーション情報

libraryDependencies ++= {
  val akkaVersion = "2.6.8"
  val akkaHttpVersion = "10.2.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "3.2.2" % "test"
  )
}

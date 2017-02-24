name := "play-java"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

resolvers += "jitpack" at "https://jitpack.io"
resolvers += "repo1" at "http://repo1.maven.org/maven2/"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  filters,
  "org.hibernate" % "hibernate-entitymanager" % "5.2.6.Final",
  "org.hibernate" % "hibernate-spatial" % "5.2.6.Final",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "dom4j" % "dom4j" % "1.6.1" intransitive(),
  "com.auth0" % "java-jwt" % "3.1.0",
  "com.typesafe.play" %% "play-mailer" % "5.0.0",
  "com.github.blocoio" % "faker" % "1.2.5",
  "com.bedatadriven" % "jackson-datatype-jts" % "2.2",
  "org.mindrot" % "jbcrypt" % "0.4"


)

name := "custom-log-for-twitter-server"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "twttr" at "https://maven.twttr.com/"

libraryDependencies ++=
  Seq(
    "com.twitter" %% "twitter-server" % "1.15.0"
  , "com.twitter" %% "util-logging" % "6.30.0-SNAPSHOT"
  )

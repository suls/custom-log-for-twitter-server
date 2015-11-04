package main

import com.twitter.app.App
import com.twitter.server._
import com.twitter.logging._

object Main extends
//  TwitterServer {
MyServer {

  def main(): Unit = {
    log.info("hello")
  }
}

trait MyLogging extends Logging {
  self: App =>

  override def defaultFormatter = new Formatter(
    timezone = Some("UTC")
    , prefix = "<yyyy-MM-dd HH:mm:ss.SSS> [%.3s] %s: "
  )
}


trait MyServer extends App
with Linters
with EventSink
with MyLogging
//with LogFormat
with Hooks
with AdminHttpServer
with Admin
with Lifecycle
with Stats

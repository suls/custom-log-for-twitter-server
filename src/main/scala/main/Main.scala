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

  import Logging._

  def myhandlers: List[() => Handler] = {
    val output = outputFlag()
    val level = Some(levelFlag())
    val handler =
      if (output == "/dev/stderr")
        ConsoleHandler(level = level)
      else
        FileHandler(
          output,
          rollPolicyFlag(),
          appendFlag(),
          rotateCountFlag(),
          level = level,
          formatter = new Formatter(
            timezone = Some("UTC")
            ,prefix = "<yyyy-MM-dd HH:mm:ss.SSS> [%.3s] %s: "
          )
        )
    //  if (asyncFlag())
    //    handler = QueueingHandler(handler, asyncMaxSizeFlag())

    handler :: Nil
  }

  override def loggerFactories: List[LoggerFactory] = {
    LoggerFactory(
      node = "",
      level = Some(levelFlag()),
      handlers = myhandlers
    ) :: Nil
  }

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

package es.richweb.rest.mocks

import cats.effect.IO
import fs2.StreamApp.ExitCode
import fs2.{Stream, StreamApp, async}
import org.http4s.ServerSentEvent
import org.http4s.server.blaze._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Properties.envOrNone

object StreamServer extends StreamApp[IO] {
  val port: Int = envOrNone("HTTP_PORT").fold(8000)(_.toInt)

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    Stream.eval(async.circularBuffer[IO, ServerSentEvent](100)).flatMap { q =>
      BlazeBuilder[IO]
        .bindHttp(port)
        .mountService(StaticService.service, "/")
        .mountService(Ping.service(q), "/stream")
        .mountService(Ping.service(q), "/ping")
        .mountService(DateStream.service, "/now")
        .serve
    }
}

package es.richweb.rest.mocks

import cats.effect.IO

import scala.util.Properties.envOrNone
import scala.concurrent.ExecutionContext.Implicits.global
import org.http4s.server.blaze._
import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode

object StreamServer extends StreamApp[IO] {
  val port: Int = envOrNone("HTTP_PORT").fold(8000)(_.toInt)

  // Stream[F, ServerSentEvent]
  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(port)
      .mountService(StaticService.service, "/")
      .mountService(Ping.service, "/stream")
      .mountService(Ping.service, "/ping")
      .mountService(DateStream.service, "/now")
      .serve
}

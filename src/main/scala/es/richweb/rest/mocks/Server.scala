package es.richweb.rest.mocks

import cats.effect.IO

import scala.util.Properties.envOrNone
import scala.concurrent.ExecutionContext.Implicits.global

import org.http4s.server.blaze._

import fs2.{Stream, StreamApp}
import fs2.StreamApp.ExitCode

object Server extends StreamApp[IO] {
  val port: Int = envOrNone("HTTP_PORT").fold(8080)(_.toInt)

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(port)
      .mountService(Greeter.service, "/")
      .mountService(CurrentDate.service, "/now")
      .mountService(ProviderService.service, "/provider")
      .mountService(StoreService.service, "/store")
      .serve
}

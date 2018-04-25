package es.richweb.rest.mocks

import cats.effect.IO
import fs2.Scheduler
import org.http4s._
import org.http4s.dsl.impl.Root
import org.http4s.dsl.io._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


object DateStream {

  val seconds = Scheduler[IO](2).flatMap(_.awakeEvery[IO](1.second))

  val service = HttpService[IO] {
    case GET -> Root => {
      Ok(seconds.map(_.toString()))
    }
  }
}

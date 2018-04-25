package es.richweb.rest.mocks

import cats.effect.IO

import es.richweb.rest.mocks.Models.Store

import io.circe.generic.auto._
import io.circe.syntax._

import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.impl.Root
import org.http4s.dsl.io._

object StoreService {
  val service = HttpService[IO] {
    case GET -> Root / id => {
      println(s"id: $id")
      Ok( Store(id, "201", Some("7A")).asJson )
    }
  }
}

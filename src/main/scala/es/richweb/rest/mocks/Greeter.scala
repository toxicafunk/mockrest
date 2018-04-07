package es.richweb.rest.mocks

import cats.effect.IO

import org.http4s.circe._
import io.circe.Json

import org.http4s._
import org.http4s.dsl.impl.Root
import org.http4s.dsl.io._


object Greeter {
  val service = HttpService[IO] {
    case GET -> Root / "hello" / name => {
      println(s"name: $name")
      Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}"),
        "user" -> Json.fromString(name)))
    }
  }
}

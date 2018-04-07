package es.richweb.rest.mocks

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import cats.effect.IO

import org.http4s.circe._
import io.circe.Json

import org.http4s._
import org.http4s.dsl.io._
import org.http4s.dsl.impl.Root


object CurrentDate {

  val service = HttpService[IO] {
    case GET -> Root / name => {
      val now: LocalDateTime = LocalDateTime.now()
      println(s"now: $name")
      Thread.sleep(29000)
      Ok(Json.obj("now" -> Json.fromString(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))),
        "user" -> Json.fromString(name)))
    }
  }
}

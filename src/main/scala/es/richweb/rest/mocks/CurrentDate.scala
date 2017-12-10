package es.richweb.rest.mocks

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import fs2.Task
import io.circe._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._

object CurrentDate {

  val service = HttpService {
    case GET -> Root / name => {
      val now: LocalDateTime = LocalDateTime.now()
      println(s"now: $name")
      Thread.sleep(29000)
      Ok(Json.obj("now" -> Json.fromString(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))),
        "user" -> Json.fromString(name)))
    }
  }
}

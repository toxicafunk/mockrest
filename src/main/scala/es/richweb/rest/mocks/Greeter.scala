package es.richweb.rest.mocks

import io.circe._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._

object Greeter {
  val service = HttpService {
    case GET -> Root / "hello" / name => {
      println(s"name: $name")
      Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}"),
        "user" -> Json.fromString(name)))
    }
  }
}

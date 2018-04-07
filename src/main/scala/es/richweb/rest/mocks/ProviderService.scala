package es.richweb.rest.mocks

import cats.effect.IO
import es.richweb.rest.mocks.Models.{ProvidedSection, Provider}

import org.http4s.circe._
import io.circe.syntax._
import io.circe.generic.auto._

import org.http4s._
import org.http4s.dsl.io._
import org.http4s.dsl.impl.Root

object ProviderService {
  val service = HttpService[IO] {
    case GET -> Root / "provider" / id => {
      println(s"id: $id")
      Ok( Provider(id, List(ProvidedSection("main", true))).asJson )
    }
  }
}

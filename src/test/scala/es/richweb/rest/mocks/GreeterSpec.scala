package es.richweb.rest.mocks

import cats.effect.IO
import org.http4s.dsl._
import org.http4s.{Method, Request, Response, Status}
import org.specs2.matcher.MatchResult

class GreeterSpec extends org.specs2.mutable.Specification {
  "HelloWorld" >> {
    "return 200" >> {
      uriReturns200()
    }
    "return hello world" >> {
      uriReturnsHelloWorld()
    }
  }

  private val retHelloWorld: Response[IO] = {
    val getHW = Request[IO](Method.GET, io.uri("/hello/world"))
    val task = Greeter.service.run(getHW)
    task.getOrElse(Response.notFound).unsafeRunSync()
  }

  private[this] def uriReturns200(): MatchResult[Status] =
    retHelloWorld.status must beEqualTo(Status.Ok)

  private[this] def uriReturnsHelloWorld(): MatchResult[String] =
    retHelloWorld.as[String].unsafeRunSync() must beEqualTo("{\"message\":\"Hello, world\"}")
}

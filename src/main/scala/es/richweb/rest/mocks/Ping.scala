package es.richweb.rest.mocks

import cats.effect.IO
import fs2.Stream
import org.http4s._
import org.http4s.dsl.impl.Root
import org.http4s.dsl.io._

import scala.collection.mutable


object Ping {

  //val queue: Stream[IO, Queue[IO, ServerSentEvent]] = Stream.eval(async.circularBuffer[IO, ServerSentEvent](100))

  //val seconds = Scheduler[IO](2).flatMap(_.awakeEvery[IO](1.second))

  val queue: mutable.Queue[ServerSentEvent] = mutable.Queue()

  val service = HttpService[IO] {
    case GET -> Root => {
      /*var v1: Vector[ServerSentEvent] = Vector()
      queue
        .flatMap(q => q.dequeue)
        .compile
        .toVector
        .unsafeRunAsync(e => e.foreach((v: Vector[ServerSentEvent]) => v1 = v))*/
      val sse: Stream[IO, ServerSentEvent] =
        if (queue.isEmpty) Stream.empty.covary[IO]
        else Stream.emit(queue.dequeue()).covary[IO]
      Ok(sse)
    }
    case POST -> Root / msg => {
      //queue.flatMap(q => q.enqueue(Stream.emit(ServerSentEvent(msg)))).compile.toVector.unsafeRunSync()
      queue.enqueue(ServerSentEvent(msg))
      Ok()
    }
  }

}

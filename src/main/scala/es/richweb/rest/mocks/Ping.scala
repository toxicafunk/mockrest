package es.richweb.rest.mocks

import cats.effect.IO
import fs2.async.mutable.Queue
import org.http4s._
import org.http4s.dsl.impl.Root
import org.http4s.dsl.io._


object Ping {

  val service = (q:Queue[IO, ServerSentEvent]) => HttpService[IO] {
    case GET -> Root => {
      Ok(q.dequeue)
    }
    case POST -> Root / msg => {
      Ok(q.enqueue1(ServerSentEvent(msg)))
    }
  }

}
/*val sse: Stream[IO, ServerSentEvent] =
       if (queue.isEmpty) Stream.empty.covary[IO]
       else Stream.emit(queue.dequeue()).covary[IO]*/
//queue.enqueue(ServerSentEvent(msg))

//val seconds = Scheduler[IO](2).flatMap(_.awakeEvery[IO](1.second))
//val queue: mutable.Queue[ServerSentEvent] = mutable.Queue()
/*var v1: Vector[ServerSentEvent] = Vector()
     queue
       .flatMap(q => q.dequeue)
       .compile
       .toVector
       .unsafeRunAsync(e => e.foreach((v: Vector[ServerSentEvent]) => v1 = v))*/

//queue.flatMap(q => q.enqueue(Stream.emit(ServerSentEvent(msg)))).compile.toVector.unsafeRunSync()
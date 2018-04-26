package es.richweb.rest.mocks

import cats.effect.IO
import fs2._
import fs2.async.mutable.Queue

import scala.concurrent.ExecutionContext.Implicits.global

object SampleCode extends App {
  val queue: Stream[IO, Queue[IO, String]] = Stream.eval(async.circularBuffer[IO, String](5))

  val element: Stream[IO, String] =
    for {
      q <- queue
      data <- q.dequeue
    } yield data

  queue.flatMap(q => q.enqueue(Stream.emit("test")))
  println(queue)
  println(element)
}

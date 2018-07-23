package es.richweb.rest.mocks

import scala.concurrent.duration._
import monix.eval.{Task, TaskApp}

import scala.language.postfixOps

object Main extends TaskApp {
  val task: Task[Unit] = Task {
    Thread.sleep(500)
    println("hello")
  }.delayExecution(1 second).doOnFinish(_ => task)

  override def runc: Task[Unit] = task
}
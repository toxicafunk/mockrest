package es.richweb.rest.mocks

import monix.eval.{Task, TaskApp}

import scala.concurrent.duration._

object Main2 extends TaskApp {
  val task: Task[Unit] = Task {
    Thread.sleep(500)
    println("hello")
  }

  override def runc: Task[Unit] = task.delayExecution(1.second).restartUntil(_ => false)
}
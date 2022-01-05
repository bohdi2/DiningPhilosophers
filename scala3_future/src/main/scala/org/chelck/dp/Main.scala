package org.chelck.dp

import org.chelck.dp.{Philosopher, PhilosopherId, Table}

import scala.concurrent.{ExecutionContext, Future, Promise, Await}
import scala.util.{Success, Failure}
import java.util.Random
import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

@main def scala3_future: Unit = {
  val numberOfPhilosophers = 1000;
  val numberOfInterations = 500;

  val table = Table(numberOfPhilosophers)
  val timings = Timings(0, 0, 0)

  val promise = Promise[Unit]
  val future = promise.future
  val foo = Foo(numberOfPhilosophers, promise)

  val philosophers = (0 until numberOfPhilosophers)
    .map(ii => Philosopher(
      table,
      ii,
      numberOfInterations,
      foo,
      timings
    ))

  philosophers.foreach(p => p.executeStageAsync())

  println("Waiting for completion")
  Await.result(future, 5 minute)
//  future onComplete {
//    case Success(()) => println("Everyone is done")
//    case Failure(t) => println("An error has occurred: " + t.getMessage)
//  }
  println("Done waiting")
}










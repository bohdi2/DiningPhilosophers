package org.chelck.dp

import scala.concurrent.{ExecutionContext, Future, Promise}

import scala.collection.mutable

class Foo(val numberOfPhilosophers: Int, promise: Promise[Unit]) {

  val philosophers: mutable.Set[Int] = (0 until numberOfPhilosophers).to(mutable.Set)
  val lock: Object = new Object

  def success(philosopherId: Int): Unit = {
    lock.synchronized {
      philosophers.subtractOne(philosopherId)

      if (philosophers.isEmpty) {
         promise.success(())
      }
    }
  }

}

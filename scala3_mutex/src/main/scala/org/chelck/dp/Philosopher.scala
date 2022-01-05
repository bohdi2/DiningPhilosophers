package org.chelck.dp

import java.util.{Objects, Random}

class Philosopher(val table: BlockingTable, val philosopherId: Int, var numberOfInterations: Int, var random: Random, var timings: Timings)
  extends Runnable {


  override def run(): Unit = {
    try {
      timings.start()
      (0 until numberOfInterations).foreach(_ -> {
        think()
        table.getLeftFork(philosopherId)
        table.getRightFork(philosopherId)
        eat()
        table.returnLeftFork(philosopherId)
        table.returnRightFork(philosopherId)
        timings.mark()
      })
    } catch {
      case e: Exception =>

    }
    println("Philosopher " + philosopherId + " is done: " + timings)
  }

  private def think(): Unit = {
  }

  private def eat(): Unit = {
    val delay = random.nextLong(2)
//    try Thread.sleep(delay)
//    catch {
//      case e: InterruptedException =>
//    }
  }
}

package org.chelck.dp

import org.chelck.dp.{Philosopher, PhilosopherId, Table}

import java.util.Random

@main def scala3_mutex: Unit =
  val numberOfPhilosophers = 200;
  val numberOfInterations = 200;

  val table = new Table(numberOfPhilosophers)
  val blockingTable = new BlockingTable(table)
  val timings = new Timings(0, 0, 0)

  val threads = for {
    philosopherId <- 0 until numberOfPhilosophers
    philosopher = new Philosopher(
      blockingTable,
      philosopherId,
      numberOfInterations,
      new Random(),
      timings
    )
    thread = new Thread(philosopher)
  }
  yield thread

  threads.foreach(_.start())
  threads.foreach(_.join())






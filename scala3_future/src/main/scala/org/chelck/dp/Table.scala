package org.chelck.dp

import scala.collection.mutable

object Table {

  def apply(numberOfPhilosophers: Int) = new Table(numberOfPhilosophers)
}

class Table(val numberOfPhilosophers: Int) {
  val lock = Object()

  private var usedForks = mutable.Set[Int]()
  private var philosophersWithForks = mutable.Map[Int, mutable.Set[Int]]()
  (0 until numberOfPhilosophers).foreach((p: Int) => this.philosophersWithForks.put(p, mutable.Set[Int]()))

  def getLeftFork(philosopher: Int): Option[Int] = {
    lock.synchronized {
      assert(philosopher >= 0 && philosopher < numberOfPhilosophers, "philosopher is " + philosopher)
      val leftFork: Int = philosopher

      if (usedForks.contains(leftFork) || isLastPhilosopher("Use left fork", philosopher)) None
      else {
        usedForks.add(leftFork)
        philosophersWithForks(philosopher).add(leftFork)
        //isLastPhilosopher("Use left fork", philosopher)
        Some(leftFork)
      }
    }
  }

  def getRightFork(philosopher: Int): Option[Int] = {
    lock.synchronized {
      assert(philosopher >= 0 && philosopher < numberOfPhilosophers, "philosopher is " + philosopher)
      val rightFork: Int = (philosopher + 1) % numberOfPhilosophers

      if (usedForks.contains(rightFork) || isLastPhilosopher("Use right fork", philosopher)) None
      else {
        usedForks.add(rightFork)
        philosophersWithForks(philosopher).add(rightFork)
        //isLastPhilosopher("Use right fork", philosopher)
        Some(rightFork)
      }
    }
  }

  def returnLeftFork(philosopher: Int): Unit = {
    lock.synchronized {
      assert(philosopher >= 0 && philosopher < numberOfPhilosophers, "philosopher is " + philosopher)
      val leftFork: Int = philosopher

      usedForks.remove(leftFork)
      philosophersWithForks(philosopher).remove(leftFork)
    }
  }

  def returnRightFork(philosopher: Int): Unit = {
    lock.synchronized {
      assert(philosopher >= 0 && philosopher < numberOfPhilosophers, "philosopher is " + philosopher)
      val rightFork: Int = (philosopher + 1) % numberOfPhilosophers
      usedForks.remove(rightFork)
      philosophersWithForks(philosopher).remove(rightFork)
    }
  }

  private def isLastPhilosopher(comment: String, philosopher: Int): Boolean = {

    val philosophersWithNoForks = for {
      (philosopherId, forks) <- this.philosophersWithForks
      if forks.isEmpty
    }
      yield philosopherId

    //println(s"comment: ${comment}, no forks: ${philosophersWithNoForks}")
    philosophersWithNoForks.size == 1 && philosophersWithNoForks.count(_ == philosopher) == 1
  }
}

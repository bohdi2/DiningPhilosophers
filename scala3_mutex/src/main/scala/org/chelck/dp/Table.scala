package org.chelck.dp

import scala.collection.mutable

class Table(val numberOfPhilosophers: Int) {
  private var usedForks = mutable.Set[Int]()
  private var philosophersWithForks = mutable.Map[Int, mutable.Set[Int]]()
  (0 until numberOfPhilosophers).foreach((p: Int) => this.philosophersWithForks.put(p, mutable.Set[Int]()))

  def getLeftFork(philosopher: Int): Option[Int] = {
    assert(philosopher >= 0 && philosopher < numberOfPhilosophers, "philosopher is " + philosopher)
    val leftFork: Int = philosopher
    //println(s"${philosopher}: ${leftFork}, UsedForks: ${usedForks}, contains: ${usedForks.contains(leftFork)}, isLast: ${isLastPhilosopher("Use left fork", philosopher)}")

    if (usedForks.contains(leftFork) || isLastPhilosopher("Use left fork", philosopher)) None
    else {
      usedForks.add(leftFork)
      philosophersWithForks(philosopher).add(leftFork)
      isLastPhilosopher("Use left fork", philosopher)
      Some(leftFork)
    }
  }

  def getRightFork(philosopher: Int): Option[Int] = {
    assert(philosopher >= 0 && philosopher < numberOfPhilosophers, "philosopher is " + philosopher)
    val rightFork: Int = (philosopher + 1) % numberOfPhilosophers
    //println(s"${philosopher}: getRighttFork: ${rightFork}, UsedForks: ${usedForks}, contains: ${usedForks.contains(rightFork)}, isLast: ${isLastPhilosopher("Use right fork", philosopher)}")

    if (usedForks.contains(rightFork) || isLastPhilosopher("Use right fork", philosopher)) None
    else {
      usedForks.add(rightFork)
      philosophersWithForks(philosopher).add(rightFork)
      isLastPhilosopher("Use right fork", philosopher)
      Some(rightFork)
    }
  }

  def returnLeftFork(philosopher: Int): Unit = {
    assert(philosopher >= 0 && philosopher < numberOfPhilosophers, "philosopher is " + philosopher)
    val leftFork: Int = philosopher

    usedForks.remove(leftFork)
    philosophersWithForks(philosopher).remove(leftFork)
    //println(s"${philosopher}: returnLeftFork: ${leftFork}, UsedForks: ${usedForks}, contains: ${usedForks.contains(leftFork)}")
  }

  def returnRightFork(philosopher: Int): Unit = {
    assert(philosopher >= 0 && philosopher < numberOfPhilosophers, "philosopher is " + philosopher)
    val rightFork: Int = (philosopher + 1) % numberOfPhilosophers
    usedForks.remove(rightFork)
    philosophersWithForks(philosopher).remove(rightFork)
    //println(s"${philosopher}: returnRightFork: ${rightFork}, UsedForks: ${usedForks}, contains: ${usedForks.contains(rightFork)}")
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

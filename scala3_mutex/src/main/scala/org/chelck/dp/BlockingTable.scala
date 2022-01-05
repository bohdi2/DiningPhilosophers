package org.chelck.dp

class BlockingTable(val table: Table) {
  val lock = new Object

  def getLeftFork(philosopher: Int): Int = {
    lock.synchronized {
      var f: Option[Int] = table.getLeftFork(philosopher)

      while (f == None) {
        f = table.getLeftFork(philosopher)
        if (f.isEmpty) lock.wait()
      }
      f.get
    }
  }

  def returnLeftFork(philosopher: Int): Unit = {
    lock synchronized {
      table.returnLeftFork(philosopher)
      lock.notifyAll()
    }

  }

  def getRightFork(philosopher: Int): Int = {
    lock.synchronized {
      var f: Option[Int] = table.getRightFork(philosopher)

      while (f == None) {
        f = table.getRightFork(philosopher)
        if (f.isEmpty) lock.wait()
      }
      f.get
    }
  }

  def returnRightFork(philosopher: Int): Unit = {
    lock synchronized {
      table.returnRightFork(philosopher)
      lock.notifyAll()
    }
  }
}

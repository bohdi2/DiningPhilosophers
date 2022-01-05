package org.chelck.dp

import java.util.{Objects, Random}
import scala.concurrent._
import ExecutionContext.Implicits.global

trait State {
  def handle(context: Philosopher): Unit
}

class Philosopher(val table: Table, val philosopherId: Int, var numberOfInterations: Int, var foo: Foo, var timings: Timings) {

  var state:State = _
  state = new NeedLeftForkState()

  def executeStageAsync(): Unit = {
    Future {
      if (executeStage()) {
        executeStageAsync()
      }
    }
  }


  // Return true if more stages need to be exexuted
  def executeStage(): Boolean = {
    if (numberOfInterations > 0)
      state.handle(this)

    numberOfInterations > 0
  }

  class NeedLeftForkState extends State {
    override def handle(context: Philosopher): Unit = {
      context.state = table.getLeftFork(philosopherId) match {
        case Some(fork) => {
          //println(s"Philosopher ${philosopherId}/${numberOfInterations} Needs left fork  and gets it")
          new NeedRightForkState
        }
        case None => {
          //println(s"Philosopher ${philosopherId}/${numberOfInterations} Needs left fork ${numberOfInterations} but doesn't get it")
          this
        }
      }
    }
  }

  class NeedRightForkState extends State {
    override def handle(context: Philosopher): Unit = {
      context.state = table.getRightFork(philosopherId) match {
        case Some(fork) => {
          //println(s"Philosopher ${philosopherId}/${numberOfInterations} Needs right fork ${numberOfInterations} and gets it")
          new EatingState
        }
        case None => {
         //println(s"Philosopher ${philosopherId}/${numberOfInterations} Needs right fork ${numberOfInterations} and doesn't get it")
          this
        }
      }
    }
  }

  class EatingState extends State {
    override def handle(context: Philosopher): Unit = {
      //println(s"Philosopher ${philosopherId}/${numberOfInterations} Eating and Returning")
      table.returnLeftFork(philosopherId)
      table.returnRightFork(philosopherId)
      context.state = new NeedLeftForkState
      numberOfInterations -= 1
      if (numberOfInterations == 0) {
        //println(s"Philosopher ${philosopherId}/${numberOfInterations} Goes to bed")
        foo.success(philosopherId)

      }
    }
  }

}




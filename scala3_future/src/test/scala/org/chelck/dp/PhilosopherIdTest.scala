package org.chelck.dp

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.Assertions._

class SetSuite extends AnyFunSuite {
  test("PhilosopherId should toString correctly") {
    assertResult("Philosopher(5)") {
      new PhilosopherId(5).toString
    }
  }
}

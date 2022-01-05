package org.chelck.dp

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.Assertions._


class TableTest extends AnyFunSuite {
  test("left forks are numbered correctly") {
    val table = new Table(5)

    assertResult(Some(0)) {
      table.getLeftFork(0)
    }

    assertResult(Some(1)) {
      table.getLeftFork(1)
    }

    assertResult(Some(2)) {
      table.getLeftFork(2)
    }

    assertResult(Some(3)) {
      table.getLeftFork(3)
    }

    assertResult(None) {
      table.getLeftFork(4)
    }
  }

  test("right forks are numbered correctly") {
    val table = new Table(5)
    assertResult(Some(1)) {
      table.getRightFork(0)
    }

    assertResult(Some(2)) {
      table.getRightFork(1)
    }

    assertResult(Some(3)) {
      table.getRightFork(2)
    }

    assertResult(Some(4)) {
      table.getRightFork(3)
    }

    assertResult(None) {
      table.getRightFork(4)
    }
  }

  test("getting same left fork twice is None") {
    val table = new Table(5)
    table.getLeftFork(3)

    assertResult(None) {
      table.getLeftFork(3)
    }
  }

  test("getting same right fork twice is None") {
    val table = new Table(5)
    table.getRightFork(3)

    assertResult(None) {
      table.getRightFork(3)
    }
  }

  test("a returned left fork can be gotten") {
    val table = new Table(5)
    table.getLeftFork(3)
    table.returnLeftFork(3)

    assertResult(Some(3)) {
      table.getLeftFork(3)
    }
  }

  test("a returned right fork can be gotten") {
    val table = new Table(5)
    table.getRightFork(3)
    table.returnRightFork(3)

    assertResult(Some(4)) {
      table.getRightFork(3)
    }
  }

  test("one out of two philosophers can get both forks") {
    val table = new Table(2)

    assertResult(Some(0)) {
      table.getLeftFork(0)
    }

    assertResult(Some(1)) {
      table.getRightFork(0)
    }

    assertResult(None) {
      table.getLeftFork(1)
    }

    assertResult(None) {
      table.getRightFork(1)
    }
  }

  test("one of two philosophers with a fork blocks the other philosopher") {
    val table = new Table(2)

    assertResult(Some(0)) {
      table.getLeftFork(0)
    }

    assertResult(None) {
      table.getLeftFork(1)
    }

    assertResult(None) {
      table.getRightFork(1)
    }
  }
}

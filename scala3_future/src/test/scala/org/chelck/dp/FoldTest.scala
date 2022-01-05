package org.chelck.dp

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.Assertions._

class FoldTest extends AnyFunSuite {

    test("foo") {

      val list = List("A", "A", "B", "C", "C")

      val expected = List(("A", 2), ("B", 1), ("C", 2))

      val acutual = list.fold(List())((accum, s) => ("X", 3) : accum)

      assert(expected == actual)

    }
}

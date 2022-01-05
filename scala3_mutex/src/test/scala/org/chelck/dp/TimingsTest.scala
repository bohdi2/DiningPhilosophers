package org.chelck.dp

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.Assertions._

class TimingsTest extends AnyFunSuite {

  test("Timings has total and max data") {
    val t = new Timings(0L, 0L, 0L)
    t.start()
    Thread.sleep(10)
    t.mark()
    assert(t.toString.contains("total="))
    assert(t.toString.contains("max="))
  }
}

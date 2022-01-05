package org.chelck.dp

import java.util.concurrent.TimeUnit

case class Timings(var startTime: Long, var total: Long, var max: Long) {

  def start(): Unit = startTime = System.nanoTime();

  def mark(): Unit = {
    val now = System.nanoTime
    val elapsed = now - this.startTime
    this.total = total + elapsed
    this.max = Math.max(this.max, elapsed)
    this.startTime = now
  }
  
  private def f(n: Long) = TimeUnit.NANOSECONDS.toMillis(n)

  override def toString: String = s"total=${f(total)}, max=${f(max)}"

}

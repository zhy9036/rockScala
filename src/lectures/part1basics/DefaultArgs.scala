package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {
  @tailrec
  def factorial(n: Int, cur: Int = 1): Int = {
    if (n == 1) cur;
    else factorial(n - 1, n * cur)
  }

  println(factorial(5))
}

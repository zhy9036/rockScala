package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  // Will StackOverFlow
  def factorial(n: Int): Int = if (n <= 1) 1 else n * factorial(n - 1)

  def betterFactorial(n: Int): BigInt = {
    @tailrec
    def helper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else helper(x - 1, x * accumulator) // Tail Recursion = use recursive call as the LAST expression
    helper(n, 1)
  }

  // When you need loops, use TAIL RECURSION


}

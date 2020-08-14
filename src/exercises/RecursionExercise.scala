package exercises

import scala.annotation.tailrec

object RecursionExercise extends App {
  @tailrec
  def cat(str: String, n: Int, rst: String): String = {
    if (n == 0) rst
    else cat(str, n - 1, str + rst)
  }
  println(cat("Hello", 3, ""))

  @tailrec
  def isPrime(n: Int, d: Int): Boolean = {
    if (d <= 1) true
    else if (n % d == 0) false
    else isPrime(n, d - 1)
  }
  println(isPrime(13 * 37, 13 * 37 / 2))

  @tailrec
  def fibonacci(n: Int, n1: Int, n2: Int): Int = {
    if (n <= 2) n1 + n2
    else fibonacci(n - 1, n2, n1 + n2)
  }
  println(fibonacci(6, 0, 1))
 }

package lectures.part1basics

object Functions extends App {
  def aFunc(a: String, b: Int): String = a + " " + b

  println(aFunc("Hello", 3))

  def aFunc(): Int = 23 // overloading
  println(aFunc)

  // In Scala, when you need loops, use recursion
  def repeated(str: String, n: Int): String = {
    if (n == 1) str
    else str + repeated(str, n - 1)
  }

  println(repeated("Hello", 3))

  def aFuncWithSideEffects(str: String): Unit = println(str)

  def aBigFunc(n: Int): Int = {
    def aSmallFunc(a: Int): Int = a + 1

    aSmallFunc(2)
  }
}

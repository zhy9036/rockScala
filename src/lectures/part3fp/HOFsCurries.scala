package lectures.part3fp

object HOFsCurries extends App {
  // Higher order function (HOF)
  // has function as parameter
  val spFun: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = (a, b) => (c) => c

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))

  def nTimes(f: Int => Int, n : Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (a: Int) => a + 1
  println(nTimes(plusOne, 10 ,0))

  def betterNTimes(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) x => x
    else x => betterNTimes(f, n - 1)(f(x))
  println(betterNTimes(plusOne, 10)(0))
  val plus10 = betterNTimes(plusOne, 10)
  println(plus10(1)) // print 11

  // curried functions
  val superAdder: Int => Int => Int = a => b => a + b
  val plus3 = superAdder(3)
  println(plus3(3)) // prints 6

  // functions with multiple parameter lists
  def curriedFormatter(s: String)(d: Double) = s.format(d)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  println(standardFormat(2.43432432)) // prints 2.43

  // to curry
  def toCurry(f: (Int, Int) => Int): Int => Int => Int = (a: Int) => (b: Int) => f(a, b)

  // from curry
  def fromCurry(f: Int => Int => Int): (Int, Int) => Int = (a, b) => f(a)(b)

  def compose(f: Int => Int, g: Int => Int): Int => Int = a => f(g(a))
  def composeGeneric[A, B, T](f: A => B, g: T => A): T => B = a => f(g(a))

  def andThen(f: Int => Int, g: Int => Int): Int => Int = a => g(f(a))
  def andThenGeneric[A, B, C](f: A => B, g: B => C): A => C = a => g(f(a))

  def superAdder2: (Int => Int => Int) = toCurry((_ + _))

}

package exercises

object FuncExercise extends App {
  def greeting(name: String, age: Int): Unit =
    println("Hi my name is " + name + " and I am " + age + " years old.")

  def factorial(n: Int): Int =
    if (n <= 0) 1 else n * factorial(n - 1)

  def fibonacci(n: Int): Int =
    if (n <= 2) 1 else fibonacci(n - 1) + fibonacci(n - 2)


  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(d: Int): Boolean = {
      if (d <= 1) true
      else (n % d != 0) && isPrimeUntil(d - 1)
    }
    isPrimeUntil(n / 2)
  }

  println(isPrime(23))
  println(isPrime(2003))
  println(isPrime(27 * 13))
}

package lectures.part2oop

object Exceptions extends App {
  val x: String = null
//  println(x.length)
//  val ex = throw new NullPointerException

  // 1. throwing exceptions
  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. catching exceptions
  def getInt(withException: Boolean): Int = {
    if (withException) throw new RuntimeException("Not int for you!")
    else 42
  }
  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => 43 // println("caught a Runtime exception")
  } finally {
    // finally is optional and doesn't influence the return type
    // use finally only for side effects
    println("finally will be executed no matter whats")
  }
  println(potentialFail)

  // 3. define own exceptions
  class MyException extends Exception
  val exception = new MyException
//  throw exception

  // OOM
  // val array = Array.ofDim(Int.MaxValue)

  // SO
//  def inf: Int = 1 + inf
//  inf

  class OverFlowException extends Exception
  class UnderFlowException extends Exception
  class MathCalculationException extends Exception
  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val rst = x + y
      if (x > 0 && y > 0 && rst < 0) throw new OverFlowException
      else if (x < 0 && y < 0 && rst >  0) throw new UnderFlowException
      else rst
    }

    def substract(x: Int, y: Int): Int = {
      val rst = x - y
      if (x > 0 && y < 0 && rst < 0) throw new OverFlowException
      else if (x < 0 && y > 0 && rst > 0) throw new UnderFlowException
      else rst
    }

    def multiply(x: Int, y: Int): Int = {
      val rst = x * y
      if (x > 0 && y > 0 && rst < 0) throw new OverFlowException
      else if (x < 0 && y < 0 && rst < 0) throw new OverFlowException
      else if (x < 0 && y > 0 && rst > 0) throw new UnderFlowException
      else if (x > 0 && y < 0 && rst > 0) throw new UnderFlowException
      else rst
    }

    def devide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      x / y
    }
  }

}

package lectures.part3fp

object AnonymousFunctions extends App {
  // anonymous functions (LAMBDA)
  val doublerWithRT: Int => Int = x => x * 2
  val doubler = (x: Int) => x * 2

  // multiple params
  val adder: (Int, Int) => Int = (a, b) => a + b

  // no params
  val sth: () => Int = () => 3

  // careful anonymous functions are different from other functions
  // lambda can only be called with ()
  println(sth) // will not call the function!
  println(sth())

  // curly braces with lambdas
  val strToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val increment: Int => Int = x => x + 1
  val niceIncrement: Int => Int = _ + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

}

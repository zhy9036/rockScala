package lectures.part4pm

object PatternsEveryWhere extends App {

  // 1. catches are actually MATCHES
  try {

  } catch {
    case e: RuntimeException => "runtime exception"
    case npe: NullPointerException => "NPE"
    case _ => "Sth else"
  }

  // 2. for-comprehensions are based on PATTERN MATCHING
  val list = List(1, 2, 3)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  // 3. structure bindings are based on PATTERN MATCHING
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple

  val head :: tail = list


  // partial function
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "sth else"
  } // partial function literal

}

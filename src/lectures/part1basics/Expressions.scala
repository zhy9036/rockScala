package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2
  println(x)

  // Instructions (DO) vs Expressions (VALUE)
  // Instructions: do/execute sth
  // Expressions: evaluate sth / get value of sth
  val condition = true
  val conditionVal = if (condition) 5 else 3

  // Never write the following in Scala
  // Everything in Scala is an expression!
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  val aWeiredValue = (i = 3) // unit === void

  // side effects: println(), whiles, reassigning

  // code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }

}

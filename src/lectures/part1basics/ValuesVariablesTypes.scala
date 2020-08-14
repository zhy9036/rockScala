package lectures.part1basics

object ValuesVariablesTypes extends App {
  // val is immutable
  // type of val is optional
  val x: Int = 23
  println(x)

  var str: String = "hello";
  val str1: String = "goodbye"

  val bool: Boolean = true
  val c: Char = 'a'
  val short: Short = 1
  val long: Long = 1l
  val float: Float = 2.1f
  val double: Double = 3.14

  // variables var
  var varInt: Int = 5
  varInt = 3 // side effects
}

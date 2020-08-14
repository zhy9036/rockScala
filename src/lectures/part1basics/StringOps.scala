package lectures.part1basics

object StringOps extends App {
  val str = " haha "
  println('1' +: str :+ '9')

  // String interpolators

  // S-Interpolators
  val name = "Jack"
  val age = 12
  val greeting = s"Hi I am $name, $age years old!"
  val greeting1 = s"Hi I am $name, ${age + 1} years old!"

  // F-Interpolators
  val speed = 1.2f

  // %2.2f means format the float that has:
  // at least 2 characters long
  // and 2 decimal precision
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  // RAW-Interpolators
  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println(raw"$escaped")
}

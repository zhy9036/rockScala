package lectures.part2oop

object OOBasics extends App {
  val person = new Person("Jack", 12)
  println(person.age)
  println(person.x)
  person.greet("Rose")
  person.greet
}

// constructor
class Person(name: String, val age: Int = 0) {
  // class body
  val x = 2;

  def greet(name: String) = {println(s"${this.name} says: Hi, $name!")}
  def greet = println("AHaHaHa")

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
// class parameters are not fields

class Writer(fname: String, lname: String, val age: Int) {
  def fullName(): String = fname + " " + lname

}

class Novel(name: String, release: Int, author: Writer) {
  def authorAge(): Int = author.age
  def isWrittenBy(author: Writer) = this.author == author
  def copy(): Novel = new Novel(name, release, author)
}

class Counter(val n: Int) {
  def curCount(): Int = n
  def increment(): Counter = new Counter(n + 1)
  def decrement(): Counter = new Counter(n - 1)
  def increment(amount: Int): Counter = new Counter(n + amount)
  def decrement(amount: Int): Counter = new Counter(n - amount)

  // functional ways
  def inc(amount: Int): Counter = {
    if (amount == 1) increment
    else increment.inc(amount - 1)
  }

  def dec(amount: Int): Counter = {
    if (amount == 1) decrement
    else decrement.inc(amount - 1)
  }
}
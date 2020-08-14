package lectures.part2oop

object Objects {
  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  def main(args: Array[String]): Unit = {
    println("what?")
  }
  object Person {
    // static/class level functionality
    val N_EYES = 2;
    def canFly: Boolean = false
    // factory method
    def from(mother: Person, father: Person): Person = new Person("Bobbie")
    def apply(m: Person, f: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String = "John Doe") {
    // instance-level functionality
  }

  // The pattern of object with class called COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = new Person; val john = new Person
  println(mary == john)
  val bobbie = Person.from(mary, john)
  val anotherBobbie = Person(mary, john)

  // Scala Applications = Scala object with
  // def main(args: Arrays[String]): Unit
}

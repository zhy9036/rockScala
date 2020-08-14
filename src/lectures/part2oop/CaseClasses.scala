package lectures.part2oop

object CaseClasses extends App {
  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 12)
  println(jim.age)

  // 2. sensible toString
  println(jim.toString)

  // 3. equals and hashCode are implemented OOTB
  val jim2 = new Person("Jim", 12)
  println(jim == jim2)
  println(jim.equals(jim2))

  // 4. case classes have handy copy method
  val jim3 = jim.copy()
  println(jim3.hashCode)

  // 5. case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. case classes are serializable
  // Akka

  // 7. case classes have extractor pattern = case classes can be used in PATTERN MATCHING

  // 8. case object is also there
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

}

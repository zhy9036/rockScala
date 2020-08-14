package lectures.part2oop

object Inheritance extends App {

  // Single class inheritance
  sealed class Animal {
    val creatureType = "wild"
    def eat = println("nom nom")
    private def canYouSee = println("emmm, no")
    protected def youCanSeeMeInSubClassImp = println("Cool!")
  }
  class Cat extends Animal {

  }
  val cat = new Cat {
    def haha = {
      youCanSeeMeInSubClassImp
    }
  }
//  cat.eat
  cat.haha

  // Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, id: String) extends Person(name, age)
  class Child(name: String, age: Int, id: String) extends Person(name)

  // Overriding
  class Dog(override val creatureType: String) extends Animal {
//    override val creatureType: String = "domestic"
    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    }
  }
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (Polymorphism)
  val sa: Animal = new Dog("K9")
  sa.eat

  // preventing overrides
  // 1. use final on member
  // 2. use final on class
  // 3. seal the class = extend classes in this file only, preventing extending from other files
}

package lectures.part2oop

object AbstractDataTypes extends App {
  // Abstract
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "K9"

    override def eat: Unit = "Crunch, Crunch"
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"

    override def eat: Unit = println("nom nom nom")

    override def eat(animal: Animal): Unit = println(s"I am a $creatureType and I eats ${animal.creatureType}")
  }
  val d = new Dog
  val c = new Crocodile
  c.eat(d)

  // traits vs abstract classes
  // 1. traits do not have constructor parameters
  // 2. multiple traits may be inherited by the same class
  // 3. traits = behavior, abstract class = "things"
}

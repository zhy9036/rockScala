package lectures.part2oop

object AnonymousClasses extends App {
  trait Animal {
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahaha")
  }

  println(funnyAnimal.getClass)
}

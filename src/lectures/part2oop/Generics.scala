package lectures.part2oop

object Generics extends App {
  class MyList[+A] {
    // use the type A
    // B is super type of A
    def add[B >: A](element: B): MyList[B] = ???
  }

  val intList = new MyList[Int]
  val strList = new MyList[String]

  class MyMap[K, V]

  // Generics Methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyIntList = MyList.empty[Int]

  // Variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // COVARIANCE
  // 1. Yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => return a list of Animal

  // INVARIANCE
  // 2. No = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // CONTRAVARIANCE
  // 3. Hell No! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // 4. bounded types

  // upper bounded
  class Cage[A <: Animal](animal: A)
  val catCage = new Cage(new Cat)
  val dogCage = new Cage(new Dog)

  class Car
  // val carCage = new Cage(new Car) // this will crash!
}

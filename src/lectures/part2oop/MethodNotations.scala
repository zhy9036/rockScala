package lectures.part2oop
import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} and ${person.name}"
    def unary_! : String = s"not $name"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }
  val mary = new Person("Mary", "Inception")
  val tom = new Person("Tom", "Fight Club")
  /* dot notation */
  println(mary.likes("Inception"))

  /* infix notation / operator notation
   only works with methods that have only one argument
   */
  println(mary likes "Inception")
  println(mary hangOutWith tom)
  println(mary + tom)
  println(1 + 2)
  println(1.+(2))

  /* prefix notation
     all about unary operators
     it only works with: - + ! ~
   */
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  println(!mary)

  /* postfix notation
     it only works methods without argument
   */
  println(mary.isAlive)
  println(mary isAlive)

  /* apply */
  println(mary.apply())
  println(mary())

}

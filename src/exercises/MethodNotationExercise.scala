package exercises
import scala.language.postfixOps
object MethodNotationExercise extends App {
  class Person(val name: String, favoriteMovie: String, val age: Int = 1) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} and ${person.name}"
    def +(nickName: String): Person = new Person(s"$name ($nickName)", favoriteMovie)
    def unary_! : String = s"not $name"
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean = true
    def learns: String = s"$name learns Scala"
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"
  }

  val mary = new Person("Mary", "Inception")
  println((mary + "the Black Widow")())
  println((+mary).age)
  println(mary learns)
  println(mary(3))
}

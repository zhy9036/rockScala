package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  // switch on steroids
  val random = new Random()
  val x = random.nextInt(10)
  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" // _ is wildcard
  }
  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I cannot drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I dont know who I am"
  }
  println(greeting)

  /*
   1. Cases are matched in order
   2. What if no cases match? MatchError
   3. type of the PM expression? The compiler will unify the type and choose lowest common ancestor
   4. PM works really well with case classes
   */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(color: String) extends Animal

  val animal: Animal = Dog("Husky")
  animal match {
    case Dog(b) => println(s"Hello, I am a $b")
  }

  // match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  // usage
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(a, b) => s"${show(a)} + ${show(b)}"
    case Prod(a, b) if a.isInstanceOf[Sum] => s"(${show(a)}) * ${show(b)}"
    case Prod(a, b) if b.isInstanceOf[Sum] => s"${show(a)} * (${show(b)})"
    case Prod(a, b) => s"${show(a)} * ${show(b)}"
    case _ => "haha"
  }
  println(show(Prod(Number(3), Sum(Number(1), Number(2)))))

  def show2(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(a, b) => s"${show2(a)} + ${show2(b)}"
    case Prod(a, b) => {
      def inner(exp: Expr) = exp match {
        case Number(_) => show2(exp)
        case Prod(_, _) => show2(exp)
        case _ => s"(${show2(exp)})"
      }
      inner(a) + " * " + inner(b)
    }
    case _ => "haha"
  }
  println(show2(Prod(Sum(Number(4), Number(3)), Sum(Number(1), Number(2)))))
}

package lectures.part2oop

import java.sql.{Date => SQLDate}
import java.util.Date

import playground.{Jack, Cinderella => CL}

// imports everything under playground
// import playground._

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  val princess = new CL
  val jack = new Jack

  // packages are in hierarchy
  // matching folder structure

  // package object
  // holds package scope public methods etc.
  sayHello
  println(SPEED_OF_LIGHT)

  val date = new Date
  // use aliasing
  val sqlDate = new SQLDate(2018, 5, 5)

  // default imports
  // java.lang --- such as String, Object, Exception
  // scala --- Int, Nothing, Function
  // scala.Predef --- println, ???
}

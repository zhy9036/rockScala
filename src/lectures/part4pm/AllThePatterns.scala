package lectures.part4pm

import exercises.{Cons, MyList, Empty}

object AllThePatterns extends App {

  // 1. Constants
  val x: Any = "Scala"
  val constant = x match {
    case 1 => "a number"
    case "Scala" => "The scala"
    case true => "The truth"
    case AllThePatterns => "A singleton object"
  }

  // 2. Match anything
  // 2.1 Wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 Variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3. Tuples
  val aTuple = (1, 2)
  val matchTuples = aTuple match {
    case (1, 1) =>
    case (a, x) => s"I've found $a"
  }
  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }

  // PMs can be nested!

  // 4. Case classes - constructor pattern
  val aList: MyList[Int] = Cons(1, Empty)
  val matchAList = aList match {
    case Empty =>
    case Cons(h, Cons(head, tail)) =>
  }

  // 5. List patterns
  val aStandardList = List(1, 2, 3, 42)
  val aslMatching = aStandardList match {
    case List(1, _, _, _) => // extractor
    case List(1, _*) => // list of arbitrary length
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // infix pattern
  }

  // 6. Type specifiers
  val unknown: Any = 2
  val unknowMatch = unknown match {
    case list: List[Int] =>
    case _ =>
  }

  // 7. Name binding (giving a pattern a name)
  val nameBindingMatch = aList match {
    case notEmptyList @ Cons(_, _) =>
    case notEmptyList =>
    case Cons(1, rest @ Cons(2, _)) =>
  }

  // 8. Multi-patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => // compound pattern (multi-pattern)
  }

  // 9. If guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(se, _)) if se % 2 == 0 =>
  }

  // Question
  // !!! JVM Type erasure !!!
  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "Strings"
    case listOfInteger: List[Int] => "Integers"
    case _ => ""
  }
  println(numbersMatch)
}

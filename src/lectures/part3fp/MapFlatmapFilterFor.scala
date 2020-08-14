package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list)

  // map
  println(list.map(_ + " is a num"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  println(list.flatMap((a: Int) => List(a, a + 1)))

  // combination
  // iterating
  val nums = List(1, 2, 3)
  val chars = List('a', 'b', 'c')
  val combo = nums.flatMap(n => chars.map(c => n + "" + c))
  println(combo)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- nums if n % 2 == 0
    c <- chars
  } yield n + "" + c
  println(forCombinations)

  for {
    n <- nums
  } println(n)

  // syntax overload
  println(list.map { x =>
    x * 2
  })
}

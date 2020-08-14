package lectures.part3fp

object WhatsAFunction extends App {
  // use functions as first class elements
  // problems: oop
  val doubler = new MyFunction[Int, Int] {
    override def apply(ele: Int): Int = ele * 2
  }
  println(doubler(2))

  // Function types = Function1[A, B]
  val strToInt = new (String => Int) {
    override def apply(str: String): Int = str.toInt
  }
  println(strToInt("1") + 2)

  // Function types Function2[A, B, R] === (A, B) => R
  // ALL SCALA FUNCTIONS ARE OBJECTS

}

trait MyFunction[A, B] {
  def apply(ele: A): B
}

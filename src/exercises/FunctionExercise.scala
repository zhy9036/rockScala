package exercises

object FunctionExercise extends App {
  val cat = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  // Function1[Int, Function1[Int, Int]]
  val spFun: (Int) => ((Int) => Int) = (a: Int) => (b: Int) => a + b

//  val spFun1: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
//    override def apply(a: Int): Int => Int = new Function[Int, Int] {
//      override def apply(b: Int): Int = a + b
//    }
//  }

  val spFunSugar: (Int) => (Int => Int) = a => b => a + b
  val spSpFunSugar: Int => Int => Int = a => a + _
  val spFunSugar1 = (a: Int) => (b: Int) => a + b
  val adder3 = spFun(3)
  println(adder3(4))
  println(spFun(3)(4)) // curried function
  println(spFunSugar(4)(3))
}

//package exercises
//
//abstract class MyList[+A] {
//
//  def head: A
//  def tail: MyList[A]
//  def isEmpty: Boolean
//  def add[B >: A](num: B): MyList[B]
//  def filter(p: MyPredicate[A]): MyList[A]
//  def map[B](p: MyTransformer[A, B]): MyList[B]
//  def ++[B >: A](list: MyList[B]): MyList[B]
//  def flatMap[B](t: MyTransformer[A, MyList[B]]): MyList[B]
//  def printElements: String
//  override def toString: String = s"[$printElements]"
//}
//
//trait MyPredicate[-T] {
//  def test(t: T): Boolean
//}
//
//trait MyTransformer[-A, B] {
//  def transform(a: A): B
//}
//
//case object Empty extends MyList[Nothing] {
//  override def head: Nothing = throw new NoSuchElementException
//
//  override def tail: MyList[Nothing] = throw new NoSuchElementException
//
//  override def isEmpty: Boolean = true
//
//  override def add[B >: Nothing](num: B): MyList[B] = new Cons(num, Empty)
//
//  override def printElements: String = ""
//
//  override def filter(p: MyPredicate[Nothing]): MyList[Nothing] = Empty
//
//  override def map[B](p: MyTransformer[Nothing, B]): MyList[B] = Empty
//
//  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
//
//  override def flatMap[B](t: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
//}
//
//case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
//  override def head: A = h
//
//  override def tail: MyList[A] = t
//
//  override def isEmpty: Boolean = false
//
//  override def add[B >: A](num: B): MyList[B] = new Cons(num, this)
//
//  override def printElements: String = {
//    if (t.isEmpty) h + ""
//    else h + " " + tail.printElements
//  }
//
//  //  override def filter(p: MyPredicate[A]): MyList[A] = {
//  //    @tailrec
//  //    def helper(l: MyList[A], rst: MyList[A]): MyList[A] = {
//  //      if (l.isEmpty) rst
//  //      else helper(l.tail, if (p.test(l.head)) new Cons[A](l.head, rst) else rst)
//  //    }
//  //    helper(this, Empty)
//  //  }
//
//  override def filter(p: MyPredicate[A]): MyList[A] = {
//    if (p.test(h)) new Cons(h, tail.filter(p))
//    else tail.filter(p)
//  }
//
//  //  override def map[B >: A](p: MyTransformer[A, B]): MyList[B] = {
//  //    @tailrec
//  //    def helper(l: MyList[A], rst: MyList[B]): MyList[B] = {
//  //      if (l.isEmpty) rst
//  //      else helper(l.tail, new Cons[B](p.transform(l.head), rst))
//  //    }
//  //    helper(this, Empty)
//  //  }
//  override def map[B](p: MyTransformer[A, B]): MyList[B] = {
//    new Cons[B](p.transform(h), tail.map(p))
//  }
//
//  override def ++[B >: A](list: MyList[B]): MyList[B] = {
//    new Cons[B](h, t ++ list)
//  }
//
//  override def flatMap[B](t: MyTransformer[A, MyList[B]]): MyList[B] = t.transform(h) ++ tail.flatMap(t)
//}
//
//object GenericsExercise extends App {
//  val intList: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  val intList1: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))
//  //  val flat: MyList[Int] = intList1.flatMap(a => new Cons[Int](a, new Cons[Int](a + 1, Empty)))
//  //  println(flat.toString)
//  println(intList1.flatMap(t = new MyTransformer[Int, MyList[Int]] {
//    override def transform(a: Int): MyList[Int] = new Cons[Int](a, new Cons[Int](a + 1, Empty))
//  }).toString)
//  //  println((intList ++ intList1).toString)
//    val filtered: MyList[Int] = intList.filter(a => a % 2 != 0)
//  //  val mapped: MyList[Any] = intList.map(a => a * 2)
//  //  println(filtered.toString)
//  //  println(mapped.toString)
//  //  val strList: MyList[String] = new Cons("a", new Cons("b", new Cons("c", Empty)))
//  //  println(intList.toString)
//  //  println(strList.toString)
//  //  val list = new Cons(1, Empty)
//  //  println(list.head)
//  //
//  //  val list1 = new Cons(2, new Cons(1, Empty))
//  //  println(list1.tail.head)
//  //  val list2 = list1.add(3)
//  //  println(list2.head)
//  //  println(list2.toString)
//}
//

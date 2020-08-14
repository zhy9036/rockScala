package exercises

import lectures.part2oop.Generics.MyList

import scala.annotation.tailrec
import scala.collection.View

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](num: B): MyList[B]

  // higher-order functions
  def filter(p: A => Boolean): MyList[A]
  def map[B](p: A => B): MyList[B]
  def flatMap[B](t: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]
  def printElements: String
  override def toString: String = s"[$printElements]"

  // HOFs
  def forEach(f: A => Unit): Unit
  def sort(f: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](s: B)(ops: (B, A) => B): B
}

trait MyPredicate[-T] {
  def test(t: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(a: A): B
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](num: B): MyList[B] = new Cons(num, Empty)
  override def printElements: String = ""
  override def filter(p: Nothing => Boolean): MyList[Nothing] = Empty
  override def map[B](p: Nothing => B): MyList[B] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  override def flatMap[B](t: Nothing => MyList[B]): MyList[B] = Empty
  // HOFs
  override def forEach(f: Nothing => Unit): Unit = ()
  override def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new IllegalArgumentException("Length doesn't match what the heck!")
    else Empty
  }
  override def fold[B](s: B)(ops: (B, Nothing) => B): B = s
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](num: B): MyList[B] = new Cons(num, this)

  override def printElements: String = {
    if (t.isEmpty) h + ""
    else h + " " + tail.printElements
  }

//  override def filter(p: MyPredicate[A]): MyList[A] = {
//    @tailrec
//    def helper(l: MyList[A], rst: MyList[A]): MyList[A] = {
//      if (l.isEmpty) rst
//      else helper(l.tail, if (p.test(l.head)) new Cons[A](l.head, rst) else rst)
//    }
//    helper(this, Empty)
//  }

  override def filter(p: A => Boolean): MyList[A] = {
    if (p(h)) new Cons(h, tail.filter(p))
    else tail.filter(p)
  }

//  override def map[B >: A](p: MyTransformer[A, B]): MyList[B] = {
//    @tailrec
//    def helper(l: MyList[A], rst: MyList[B]): MyList[B] = {
//      if (l.isEmpty) rst
//      else helper(l.tail, new Cons[B](p.transform(l.head), rst))
//    }
//    helper(this, Empty)
//  }
  override def map[B](p: A => B): MyList[B] = new Cons[B](p(h), tail.map(p))
  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons[B](h, t ++ list)
  override def flatMap[B](t: A => MyList[B]): MyList[B] = t(h) ++ tail.flatMap(t)

  // HOFs
  override def forEach(f: A => Unit): Unit = {
    f(h)
    t.forEach(f)
  }

  override def sort(f: (A, A) => Int): MyList[A] = {
    def insert(a: A, value: MyList[A]): MyList[A] = {
      if (value.isEmpty) new Cons[A](a, Empty)
      else if (f(a, value.head) <= 0) new Cons[A](a, value)
      else new Cons[A](value.head, insert(a, value.tail))
    }
    val sortedTail = t.sort(f)
    insert(h, sortedTail)
  }
  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new IllegalArgumentException("Length doesn't match what the heck!")
    new Cons(zip(h, list.head), tail.zipWith(list.tail, zip))
  }
  override def fold[B](s: B)(ops: (B, A) => B): B = t.fold(ops(s, h))(ops)
}

object GenericsExercise extends App {
  val intList: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  intList.forEach(a => println(a))
  println("folded intlist: " + intList.fold(0)(_ + _))
  val sorted = intList.sort((a, b) => b - a)
  println(sorted)
  val intList1: MyList[Int] = new Cons(4, new Cons(5, Cons(6, Empty)))
  val zipped = intList.zipWith[Int, String](intList1, _ + _ + "-HA")
  println(zipped)
//  val flat: MyList[Int] = intList1.flatMap(a => new Cons[Int](a, new Cons[Int](a + 1, Empty)))
//  println(flat.toString)
  println(intList1.flatMap(t = (a: Int) => new Cons[Int](a, new Cons[Int](a + 1, Empty))).toString)

  println(for {
    n <- intList
    n1 <- intList1
  } yield n + "-" + n1)
//  println((intList ++ intList1).toString)
//  val filtered: MyList[Int] = intList.filter(a => a % 2 != 0)
//  val mapped: MyList[Any] = intList.map(a => a * 2)
//  println(filtered.toString)
//  println(mapped.toString)
//  val strList: MyList[String] = new Cons("a", new Cons("b", new Cons("c", Empty)))
//  println(intList.toString)
//  println(strList.toString)
//  val list = new Cons(1, Empty)
//  println(list.head)
//
//  val list1 = new Cons(2, new Cons(1, Empty))
//  println(list1.tail.head)
//  val list2 = list1.add(3)
//  println(list2.head)
//  println(list2.toString)
}


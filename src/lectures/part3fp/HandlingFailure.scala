package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}


object HandlingFailure extends App {
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU")
  // Try object via apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"
  val failbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(failbackTry)

  // if you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("WHOOPS"))
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterRst = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 2)))
  println(aSuccess.filter(_ > 10))

  // Usage example
  val hostname = "localhost"
  val port = "15432"
  def renderHTML(page: String) = println(page)

  class Conn {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean())  "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }
  }

  object HttpSvc {
    val random = new Random(System.nanoTime())
    def getConn(host: String, port: String): Conn = {
      if (random.nextBoolean()) new Conn
      else throw new RuntimeException("Port unavailable")
    }
  }

  // if you get html page from Conn, print it to the console (call renderHTML)
  println("rst: ")
  Try(HttpSvc.getConn(hostname, port)).flatMap(c => Try(c.get("sth"))).foreach(renderHTML)

  for {
    c <- Try(HttpSvc.getConn(hostname, port))
    html <- Try(c.get("/home"))
  } yield renderHTML(html)
}

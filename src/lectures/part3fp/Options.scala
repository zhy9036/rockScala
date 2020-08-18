package lectures.part3fp

import scala.util.Random

object Options extends App {
  val myFirstOption: Option[Int] = Option(4)
  val noOption: Option[Int] = None
  println(myFirstOption)
  println(noOption)

  // unsafe APIs
  def unsafeMethod(): String = null
  val rst = Option(unsafeMethod)

  // chained method
  def backupMethod(): String = "A valid rst"
  val chainedRst = Option(unsafeMethod).orElse(Option(backupMethod))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedRst = betterUnsafeMethod orElse betterBackupMethod

  // functions on options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ > 2))
  println(myFirstOption.flatMap(x => Option(x * 10)))


  // usage example

  val config: Map[String, String] = Map(
    "host" -> "0.0.0.0",
    "port" -> "15321"
  )

  class Conn {
    def connect = "Connected"
  }

  object Conn {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Conn] = {
      if (random.nextBoolean()) Some(new Conn)
      else None
    }
  }

  // try to establish the connection
  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Conn(h, p)))
  val connStatus = connection.map(_.connect)
  println(connStatus)

  // 1 liner way
  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Conn(h, p)))
    .map(_.connect)
    .foreach(println)

  // for-comprehensions
  val status = for {
    host <- config.get("host")
    port <- config.get("port")
    c <- Conn(host, port)
  } yield c.connect
  status.foreach(println)

}

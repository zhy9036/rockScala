package exercises

case class SocialNetworks(var sn: Map[String, List[String]]) {
  def add(name: String) = {
    sn += name -> List()
    sn
  }

  def remove(name: String) = {
    for {
      n <- sn(name)
    } yield {
      sn += n -> sn(n).filter(!_.equals(name))
    }
    sn -= name
  }

  def makeFriend(name1: String, name2: String) = {
    if (!sn.contains(name1) || !sn.contains(name2))
      throw new NoSuchElementException
    if (!sn(name1).contains(name2))
      sn += name1 -> (sn(name1) :+ name2)
    if (!sn(name2).contains(name1))
      sn += name2 -> (sn(name2) :+ name1)
    sn
  }

  def unFriend(name1: String, name2: String) = {
    if (!sn.contains(name1) || !sn.contains(name2))
      throw new NoSuchElementException
    if (sn(name1) contains name2)
      sn += name1 -> sn(name1).filter(n => !n.equals(name2))
    if (sn(name2) contains name1)
      sn += name2 -> sn(name2).filter(n => !n.equals(name1))
    sn
  }

  def mostFirends(): String = sn.maxBy(p => p._2.size)._1

  def friendsCount(name: String): Int = {
    if (!sn.contains(name))
      throw new NoSuchElementException
    sn(name).length
  }

  def poorPeopleCount(): Int = {
    sn.view.filterKeys(sn(_).length == 0).size
  }

  def knows(n1: String, n2: String): Boolean = {
    def bfs(target: String, neighbors: List[String], visited: List[String]): Boolean = {
      if (neighbors.isEmpty) false
      else {
        val p = neighbors.head
        if (p == target) true
        else if (visited.contains(p)) bfs(target, neighbors.tail, visited)
        bfs(target, neighbors.tail ++ sn(p), visited :+ p)
      }
    }
    bfs(n1, sn(n2), List())
  }
}

object Test extends App {
  val myNetwork = SocialNetworks(Map())
  myNetwork.add("tom")
  myNetwork.add("gatsby")
  myNetwork.makeFriend("tom", "gatsby")
  println(myNetwork.poorPeopleCount())
  println(myNetwork.sn)
  myNetwork.unFriend("tom", "gatsby")
  println(myNetwork.sn)
  println(myNetwork.poorPeopleCount())
  println(myNetwork.remove("tom"))
}

package projectfs.files

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {
  def hasEntry(name: String): Boolean = findEntry(name) != null
  def getAllFolderInPath: List[String] = {
    // remove the leading "/"
    path.substring(1).split(Directory.SEPARATOR).toList.filter(!_.isEmpty)
  }

  def findDescendant(path: List[String]): Directory = {
    if (path.isEmpty || path.head.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendant(path.tail)
  }

  def addEntry(newEntry: DirEntry): Directory = new Directory(parentPath, name, contents :+ newEntry)

  def findEntry(name: String): DirEntry = {
    @tailrec
    def helper(name: String, content: List[DirEntry]): DirEntry = {
      if (content.isEmpty) null
      else if(content.head.name.equals(name)) content.head
      else helper(name, content.tail)
    }
    helper(name, contents)
  }
  def replaceEntry(name: String, newEntry: DirEntry): Directory = {
    new Directory(parentPath, this.name, contents.filter(!_.name.equals(name)) :+ newEntry)
  }

  def isRoot: Boolean = parentPath.isEmpty

  override def asDirectory: Directory = this

  override def getType: String = "Directory"

  override def asFile: File = throw new IllegalArgumentException("A directory cannot be converted into a file")
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")
  def empty(parentPath: String, name: String): Directory = new Directory(parentPath, name, List())
}
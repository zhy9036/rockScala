package projectfs.cmds

import projectfs.files.{DirEntry, Directory}

import scala.annotation.tailrec

class Cd(path: String) extends Command {
  override def apply(state: State): State = {
    val root = state.root
    val wd = state.wd
    val absolutePath =
      if (path.startsWith(Directory.SEPARATOR)) path
      else if (wd.isRoot) wd.path + path
      else wd.path + Directory.SEPARATOR + path
    val destinationEntry = findEntry(root, absolutePath)
    if (destinationEntry == null)
      state.setMessage(s"cd: no such file or directory: $path")
    else if (!destinationEntry.getType.equals("Directory"))
      state.setMessage(s"cd: not a directory: $path")
    else {
//      println(destinationEntry.asDirectory.isRoot)
//      println(destinationEntry.asDirectory.parentPath)
//      println(destinationEntry.asDirectory.name)
      State(root, destinationEntry.asDirectory)
    }
  }

  def findEntry(root: Directory, absolutePath: String): DirEntry = {
    @tailrec
    def helper(cur: Directory, tokens: List[String]): DirEntry = {
      if (tokens.isEmpty || tokens(0).isEmpty) cur
      else if(tokens.tail.isEmpty) cur.findEntry(tokens.head)
      else {
        val child = cur.findEntry(tokens.head)
        if (child == null || !child.getType.equals("Directory")) null
        else helper(child.asDirectory, tokens.tail)
      }
    }
    val tokens = absolutePath.substring(1).split(Directory.SEPARATOR).toList
    helper(root, tokens)
  }
}

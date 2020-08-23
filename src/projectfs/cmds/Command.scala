package projectfs.cmds

import projectfs.cmds

trait Command {
  def apply(state: State): State
}

object Command {
  val MKDIR = "mkdir"
  val LS = "ls"
  val TOUCH = "touch"
  val PWD = "pwd"
  val CD = "cd"
  def emptyCmd: Command = (s: State) => s
  def incompleteCmd(name: String): Command = _.setMessage(name + ": incomplete command!")
  def from(input: String): Command = {
    val tokens = input.split(" ")
    if (input.isEmpty || tokens.isEmpty) return emptyCmd
    val cmd: Command = tokens.head match {
      case MKDIR if tokens.length < 2 => incompleteCmd("mkdir")
      case MKDIR => new Mkdir(tokens.tail.head)
      case LS => new Ls
      case PWD => new Pwd
      case TOUCH => new Touch(tokens.tail.head)
      case CD => new Cd(tokens.tail.head)
    }
    cmd
  }
}

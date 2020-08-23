package projectfs.filesystem

import java.util.Scanner

import projectfs.cmds.{Command, State}
import projectfs.files.Directory

object Filesystem extends App {
  val root = Directory.ROOT
  var state = State(root ,root)
  val scanner = new Scanner(System.in)
  while (true) {
    state.show
    state = Command.from(scanner.nextLine())(state)
  }
}

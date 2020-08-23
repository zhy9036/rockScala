package projectfs.cmds

import projectfs.files.DirEntry

class Ls extends Command {
  override def apply(state: State): State = {
    val content = state.wd.contents
    val prettyOutput = createOutput(content)
    state.setMessage(prettyOutput)
  }

  def createOutput(content: List[DirEntry]):String = {
    if (content.isEmpty) ""
    else {
      val entry = content.head
      entry.name + "[" + entry.getType + "] " + "\n" + createOutput(content.tail)
    }
  }
}

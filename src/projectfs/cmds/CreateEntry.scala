package projectfs.cmds

import projectfs.files.{DirEntry, Directory}

abstract class CreateEntry(name: String) extends Command {
  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name))
      state.setMessage(s"Entry $name already exists!")
    else if (name.contains(Directory.SEPARATOR))
      state.setMessage(s"$name must not contains separators!")
    else if (!check(name))
      state.setMessage(s"$name: illegal entry name!")
    else
      doCreateEntry(state, name)
  }
  def check(name: String): Boolean = !name.contains(".")
  def doCreateEntry(state: State, name: String): State = {
    def updateStructure(current: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) current.addEntry(newEntry)
      else {
        val oldEntry = current.findEntry(path.head).asDirectory
        current.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }
    val wd = state.wd
    val allDirsInPath = wd.getAllFolderInPath

    // TODO: implement this
    val newEntry = createSpecificEntry(state, name)
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)
    val newWd = newRoot.findDescendant(allDirsInPath)
    State(newRoot, newWd)
  }

  def createSpecificEntry(state: State, name: String): DirEntry
}

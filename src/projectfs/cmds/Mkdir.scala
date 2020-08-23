package projectfs.cmds

import projectfs.files.{DirEntry, Directory}

class Mkdir(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State, name: String): DirEntry = Directory.empty(state.wd.path, name)
}

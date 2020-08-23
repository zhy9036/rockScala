package projectfs.cmds
import projectfs.files.{DirEntry, File}

class Touch(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State, name: String): DirEntry = File.empty(state.wd.path, name)
}

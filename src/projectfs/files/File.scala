package projectfs.files

class File(override val parentPath: String, override val name: String, contents: String)
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory = throw new IllegalArgumentException("A file cannot be converted to a directory")

  override def getType: String = "File"

  override def asFile: File = this
}

object File {
  def empty(parentPath: String, name: String): File = new File(parentPath, name, "")
}
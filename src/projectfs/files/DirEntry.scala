package projectfs.files

abstract class DirEntry(val parentPath: String, val name: String) {
  def path: String = (if (parentPath.equals(Directory.ROOT_PATH)) "" else parentPath) + Directory.SEPARATOR + name
  def asDirectory: Directory
  def asFile: File
  def getType: String
}

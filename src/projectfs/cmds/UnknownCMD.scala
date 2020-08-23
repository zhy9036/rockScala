package projectfs.cmds

class UnknownCMD extends Command {
  override def apply(state: State): State = state.setMessage("CMD not found")
}

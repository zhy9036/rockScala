package projectfs.cmds

class Pwd extends Command {
  override def apply(state: State): State = state.setMessage(state.wd.path)
}

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.close();
    }
    public boolean isExit() {
        return true;
    }
}

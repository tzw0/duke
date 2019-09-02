public class ListCommand extends Command{
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.print_this(tasks.print_list());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

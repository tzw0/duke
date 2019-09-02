public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return  ui.print_this(tasks.find(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

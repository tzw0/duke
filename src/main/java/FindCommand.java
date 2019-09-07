/**
 * Represents a command that finds items from tasks
 */
public class FindCommand extends Command {
    private String keyword;
    /**
     * Creates a new FindCommand object with the given keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Executes the FindCommand
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return  ui.print_this(tasks.find(keyword));
    }
}

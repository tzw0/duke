/**
 * Represents a command that exits from the programme
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.is_exit = true;
        return ui.close();
    }
}

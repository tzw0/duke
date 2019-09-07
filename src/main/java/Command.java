/**
 * Represents a command registered in Duke
 */
public abstract class Command {
    protected boolean is_exit = false;
    /**
     * Executes the command
     * @param tasks The task list that you want to execute the command on
     * @param ui The user interface object used to respond to the user
     * @param storage The storage object used to save the changes made by the executed command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    /**
     * Checks if the programme should exit
     * @return True if the programme should exit
     */
    public boolean isExit() {
        return is_exit;
    };
}

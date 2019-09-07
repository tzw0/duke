/**
 * Represents a command that adds an item to tasks
 */
public class AddCommand extends Command {
    private String description;
    private String tasktype;
    private String datetime;
    /**
     * Creates a new AddCommand object with the given type of task and description
     */
    public AddCommand(String tasktype, String description) {
        this.description = description;
        this.tasktype = tasktype;
    }
    /**
     * Creates a new AddCommand object with the given type of task, description and date time
     */
    public AddCommand(String tasktype, String description, String datetime) {
        this.description = description;
        this.tasktype = tasktype;
        this.datetime = datetime;
    }
    /**
     * Executes the AddCommand and saves changes to storage
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasktype.equals("todo")) tasks.add(new Todo(description, false));
        else if (tasktype.equals("deadline")) tasks.add(new Deadline(description, datetime, false));
        else if (tasktype.equals("event")) tasks.add(new Event(description, datetime, false));
        else throw new DukeException("add error");
        storage.save(tasks);
        return ui.showTaskAdded(tasks.get(tasks.size()-1).toString(), tasks.size());
    }
}
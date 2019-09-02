public class AddCommand extends Command {
    private String description;
    private String tasktype;
    private String datetime;
    public AddCommand(String tasktype, String description) {
        this.description = description;
        this.tasktype = tasktype;
    }
    public AddCommand(String tasktype, String description, String datetime) {
        this.description = description;
        this.tasktype = tasktype;
        this.datetime = datetime;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasktype.equals("todo")) tasks.add(new Todo(description, false));
        else if (tasktype.equals("deadline")) tasks.add(new Deadline(description, datetime, false));
        else if (tasktype.equals("event")) tasks.add(new Event(description, datetime, false));
        else throw new DukeException("add error");
        return ui.showTaskAdded(tasks.get(tasks.size()-1).toString(), tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

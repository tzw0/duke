public class DoneCommand extends Command {
    private int index;
    public DoneCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index >= tasks.size()) throw new DukeException("index error");
        String output = ui.showTaskDone(tasks.get(this.index).describe());
        tasks.doneTask(this.index);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

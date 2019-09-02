public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.index >= tasks.size()) throw new DukeException("index error");
        String output = ui.showTaskDelete(tasks.get(this.index).toString(),tasks.size()-1);
        tasks.deleteTask(index);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

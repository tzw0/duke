public class Todo extends Task{
    public Todo(String description, boolean b)  throws DukeException{
        super(description, b);
        super.tt = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

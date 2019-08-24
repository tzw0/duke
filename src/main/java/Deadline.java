public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by)  throws DukeException{
        super(description, false);
        if (by.isBlank()) {
            throw new DukeException("blank by");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
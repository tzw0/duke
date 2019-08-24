public class Event extends Task{
    protected String at;

    public Event(String description, String at)  throws DukeException{
        super(description, false);
        if (at.isBlank()) {
            throw new DukeException("blank at");
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

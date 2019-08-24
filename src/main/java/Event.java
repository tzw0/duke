import java.text.ParseException;

public class Event extends Task{
    protected String at;

    public Event(String description, String at)  throws DukeException{
        super(description, false);
        if (at.isBlank()) {
            throw new DukeException("blank at");
        }
        this.at = DatetimeCustom.format(at);
        super.tt = "E";
        super.extra = this.at;
    }

    public Event(String description, String at, boolean b)  throws DukeException{
        super(description, b);
        if (at.isBlank()) {
            throw new DukeException("blank at");
        }
        this.at = DatetimeCustom.format(at);
        super.tt = "E";
        super.extra = this.at;
    }
    @Override
    public String toString() {
        try {
            return "[E]" + super.toString() + " (at: " + DatetimeCustom.view(at) + ")";
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

import java.text.ParseException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by)  throws DukeException{
        super(description, false);
        if (by.isBlank()) {
            throw new DukeException("blank by");
        }
        this.by = DatetimeCustom.format(by);
        super.tt = "D";
        super.extra = this.by;
    }
    public Deadline(String description, String by,boolean b)  throws DukeException{
        super(description, b);
        if (by.isBlank()) {
            throw new DukeException("blank by");
        }
        this.by = DatetimeCustom.format(by);
        super.tt = "D";
        super.extra = this.by;
    }

    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() + " (by: " + DatetimeCustom.view(by) + ")";
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
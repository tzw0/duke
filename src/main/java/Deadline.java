import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Deadline extends Task {
    protected Date by;
    DateFormat fmt = new SimpleDateFormat("dd MMMM yyyy, h:mm a", Locale.US);

    public Deadline(String description, String by) throws DukeException, ParseException {
        super(description, false);
        if (by.isBlank()) {
            throw new DukeException("blank by");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hhmm");
        Date date = sdf.parse(by);
        this.by = date;
        super.tt = "D";
        super.extra = by;
    }
    public Deadline(String description, String by,boolean b) throws DukeException, ParseException {
        super(description, b);
        if (by.isBlank()) {
            throw new DukeException("blank by");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hhmm");
        by = by.trim();
        Date date = sdf.parse(by);
        this.by = date;
        super.tt = "D";
        super.extra = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  DatetimeCustom.view(this.fmt.format(by)) + ")";
    }
}
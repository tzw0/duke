import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Deadline extends Task {
    protected Date by;
    DateFormat fmt = new SimpleDateFormat("dd MMMM yyyy, h:mm a", Locale.US);

    public Deadline(String description, String by,boolean b) throws DukeException {
        super(description, b);
        if (by.isBlank()) {
            throw new DukeException("blank by");
        } try {
            DatetimeCustom.check(by);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hhmm");
            by = by.trim();
            Date date = sdf.parse(by);
            this.by = date;
            super.tt = "D";
            super.extra = by;
        } catch (ParseException e) {
            throw new DukeException("datetime");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  DatetimeCustom.view(this.fmt.format(by)) + ")";
    }
}
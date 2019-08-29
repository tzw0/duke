import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Event extends Task{
    protected Date at;
    DateFormat fmt = new SimpleDateFormat("dd MMMM yyyy, h:mm a", Locale.US);

    public Event(String description, String at) throws DukeException, ParseException {
        super(description, false);
        if (at.isBlank()) {
            throw new DukeException("blank at");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hhmm");
        Date date = sdf.parse(at);
        this.at = date;
        super.tt = "E";
        super.extra = at;
    }

    public Event(String description, String at, boolean b) throws DukeException, ParseException {
        super(description, b);
        if (at.isBlank()) {
            throw new DukeException("blank at");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hhmm");
        Date date = sdf.parse(at);
        this.at = date;
        super.tt = "D";
        super.extra = at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DatetimeCustom.view(this.fmt.format(at)) + ")";
    }
}

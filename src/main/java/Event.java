import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Represents an Event-typed-task
 */
public class Event extends Task{
    protected Date at;
    DateFormat fmt = new SimpleDateFormat("dd MMMM yyyy, h:mm a", Locale.US);
    /**
     * Creates an Event object with the given description, datetime and whether it is done
     */
    public Event(String description, String at, boolean b) throws DukeException{
        super(description, b);
        if (at.isBlank()) {
            throw new DukeException("blank at");
        }
        try {
            DatetimeFormatter.check(at);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hhmm");
            Date date = sdf.parse(at);
            this.at = date;
            super.tt = "D";
            super.extra = at;
        } catch (ParseException e){
            throw new DukeException("datetime");
        }
    }
    /**
     * Represents the object of this class as a string
     * @return that string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DatetimeFormatter.view(this.fmt.format(at)) + ")";
    }
}

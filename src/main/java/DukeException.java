public class DukeException extends Exception {
    String error;
    public DukeException(String e) {
        this.error = e;
    }
    public String toString() {
        return "DukeException[" + error + "]";
    }
    public String response() {
        if(this.error.equals("datetime")) {
            return "☹ OOPS!!! Please enter the datetime in this format: dd/mm/yyyy time(24hr)";
        } else if(this.error.equals("blank at")) {
            return ("☹ OOPS!!! The time and date of an event cannot be empty.");
        } else if(this.error.equals("blank by")) {
            return ("☹ OOPS!!! The time and date of a deadline cannot be empty");
        } else if(this.error.equals("datetime_N")) {
            return ("☹ OOPS!!! Please ensure you have entered a valid date time (eg: mm should not exceed 12)");
        }
        return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

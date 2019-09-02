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
            return "☹ OOPS!!! Please enter the datetime in this format: dd/mm/yyyy HHMM";
        } else if(this.error.equals("unknown")) {
            return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (this.error.equals("arg1 error find")) {
            return "☹ OOPS!!! Please enter a keyword or phrase for your search";
        } else if (this.error.equals("arg1 error todo")) {
            return "☹ OOPS!!! The description of a todo cannot be blank";
        } else if (this.error.equals("arg1 error deadline")) {
            return "☹ OOPS!!! The description of a deadline cannot be blank";
        } else if (this.error.equals("arg1 error event")) {
            return "☹ OOPS!!! The description of an event cannot be blank";
        } else if (this.error.equals("index error")) {
            return "☹ OOPS!!! Please enter a valid task index number";
        }
        return ("☹ OOPS!!! " + this.error);
    }
}

import static java.lang.System.*;

public class Ui {
    private String line = " ____________________________________________________________";
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public String print_welcome() {
        System.out.println(line);
        System.out.print(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
        return "Hello! I'm Duke" + "\nWhat can I do for you?";
    }
    public String print_line() {
        System.out.println(line);
        return "";
    }
    public String print_this(String thingy) {
        System.out.println(thingy);
        return thingy;
    }
    public String unknown_command() {
        String out_ = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(out_);
        return out_;
    }
    public String close() {
        System.out.println("Bye. Hope to see you again soon!");
        print_line();
        exit(0);
        return "";
    }
    public String showLoadingError() {
        String out_ = " ☹ OOPS!!! I'm sorry, but your saved file cannot be found";
        System.out.println(out_);
        return out_;
    }
    public String showNoTasksError() {
        String out_ = "☹ You have no tasks in your list";
        System.out.println(out_);
        return out_;
    }
    public String showDatetimeError(String type) {
        String out_ = "☹ OOPS!!! The datetime of the " +type + " is invalid";
        System.out.println(out_);
        return out_;
    }
    public String showEmptyTaskError(String type) {
        String out_ = "☹ OOPS!!! The description of a " +type + " cannot be empty.";
        System.out.println(out_);
        return out_;
    }
    public String showTaskDone(String task) {
        String out_ = "Nice! I've marked this task as done:\n" + "[✓] " + task;
        System.out.println(out_);
        return out_;
    }
    public String showTaskDelete(String task, int size) {
        String out_ = "Noted. I've removed this task:" + "\n" + "\t" + task + "\n" + "Now you have " + Integer.toString(size) + " tasks in the list.";
        System.out.println(out_);
        return out_;
    }
    public String showTaskAdded(String task, int size) {
        String out_ = "Got it. I've added this task:" + "\n" + "\t" + task  +"\n" + "Now you have " + Integer.toString(size) + " tasks in the list.";
        System.out.println(out_);
        return out_;
    }
}

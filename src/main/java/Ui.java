import java.util.Scanner;

import static java.lang.System.*;

public class Ui {
    private String line = " ____________________________________________________________";
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public String showWelcome() {
        System.out.println(line);
        System.out.print(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
        return "Hello! I'm Duke" + "\nWhat can I do for you?";
    }
    public String showLine() {
        System.out.println(line);
        return "";
    }
    public String print_this(String thingy) {
        System.out.println(thingy);
        return thingy;
    }
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public String close() {
        System.out.println("Bye. Hope to see you again soon!");
        return "";
    }
    public String showError(String error_msg) {
        String out_ = "";
        if(error_msg.equals("datetime")) {
            out_ =  "☹ OOPS!!! Please enter the datetime in this format: dd/mm/yyyy HHMM";
        } else if(error_msg.equals("unknown")) {
            out_ =  ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (error_msg.equals("arg1 error find")) {
            out_ =  "☹ OOPS!!! Please enter a keyword or phrase for your search";
        } else if (error_msg.equals("arg1 error todo")) {
            out_ =  "☹ OOPS!!! The description of a todo cannot be blank";
        } else if (error_msg.equals("arg1 error deadline")) {
            out_ =  "☹ OOPS!!! The description of a deadline cannot be blank";
        } else if (error_msg.equals("arg1 error event")) {
            out_ =  "☹ OOPS!!! The description of an event cannot be blank";
        } else if (error_msg.equals("index error")) {
            out_ =  "☹ OOPS!!! Please enter a valid task index number";
        } else if (error_msg.equals("empty list")) {
            out_ =  "☹ You have no tasks in your list";
        } else if (error_msg.equals("empty task")) {
            out_ ="☹ OOPS!!! The description of a task cannot be empty.";
        }
        else out_ =  ("☹ OOPS!!! " + error_msg);
        System.out.println(out_);
        return out_;
    }
    public String showLoadingError() {
        String out_ = " ☹ OOPS!!! I'm sorry, but your saved file cannot be found";
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

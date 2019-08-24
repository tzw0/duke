import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String line = " ____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.print(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
        Scanner input = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<Task>();
        int size_ = 0;
        while (true) {
            Boolean no_error = true;
            String command = input.nextLine();
            System.out.println(line);
            if (command.isBlank()) {
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
                continue;
            }
            String sentence[] = command.split(" ");
            if (command.equals("list")) {
                if (size_==0) {
                    System.out.println("☹ You have no tasks in your list");
                    System.out.println(line);
                    continue;
                }
                System.out.println("Here are the tasks in your list:");
                int x = 0;
                Iterator itr=myList.iterator();
                while(itr.hasNext()){
                    System.out.print(Integer.toString(x + 1) + ".");
                    System.out.println(itr.next().toString());
                    x ++;
                }
            }
            else if (command.equals("bye")) break;
            else if (sentence[0].equals("done") || sentence[0].equals("delete")) {
                int index = -1;
                try {
                    index = Integer.parseInt(sentence[1]) -1;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! No tasks index specified");
                    System.out.println(line);
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please enter the index number you want to mark as done");
                    System.out.println(line);
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! No tasks index specified");
                    System.out.println(line);
                    continue;
                }
                if (size_ > index && index >= 0) {
                    if (sentence[0].equals("done")) {
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[✓] " + myList.get(index).describe());
                        myList.get(index).done(true);
                    }
                    else if (sentence[0].equals("delete")) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("\t" + myList.get(index).toString());
                        myList.remove(index);
                        size_ = myList.size();
                        System.out.println("Now you have " + Integer.toString(size_) + " tasks in the list.");
                    }
                }
                else {
                    System.out.println("☹ OOPS!!! There are no such tasks with this index, you have "+ Integer.toString(size_ ) + " task(s) in the list.");
                }
            }
            else if (sentence[0].equals("todo") || sentence[0].equals("deadline") || sentence[0].equals("event")) {
                if (sentence[0].equals("todo")) {
                    try {
                        myList.add(new Todo(command.substring(5)));
                    } catch (DukeException e) {
                        no_error = false;
                        if (e.equals("empty task"))
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    } catch (StringIndexOutOfBoundsException e) {
                        no_error = false;
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (sentence[0].equals("deadline")) {
                    try {
                        Integer end_by = command.indexOf(" /by ");
                        String by = command.substring(end_by + 5);
                        if (end_by.equals(-1)) {
                            throw new DukeException("blank by");
                        }
                        myList.add(new Deadline(command.substring(9, (end_by >= 0 ? end_by : command.length())), by));
                    } catch (DukeException e) {
                        no_error = false;
                        if (e.equals("empty task"))
                            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        else {
                            System.out.println("☹ OOPS!!! The deadline of a deadline cannot be empty");
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        no_error = false;
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else if (sentence[0].equals("event")) {
                    try {
                        Integer end_at = command.indexOf(" /at ");
                        String at = command.substring(end_at + 5);
                        if (end_at.equals(-1)) {
                            throw new DukeException("blank at");
                        }
                        myList.add(new Event(command.substring(6, end_at >= 0 ? end_at: command.length()), at));
                    } catch (DukeException e) {
                        no_error = false;
                        if (e.equals("empty task"))
                            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        else
                            System.out.println("☹ OOPS!!! The time and date of an event cannot be empty.");
                    } catch (StringIndexOutOfBoundsException e) {
                        no_error = false;
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                }
                if (no_error) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + myList.get(size_).toString());
                    System.out.println("Now you have " + Integer.toString(size_ + 1) + " tasks in the list.");
                    size_ = myList.size();
                }
            }
            else {
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
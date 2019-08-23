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
        Task[] myList = new Task[100];
        int size_ = 0;
        while (true) {
            String command = input.nextLine();
            String sentence[] = command.split(" ");
            System.out.println(line);
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int x = 0; x < size_; x ++) {
                    System.out.print(Integer.toString(x + 1) + ".");
                    System.out.println(myList[x].toString());
                }
            }
            else if (command.equals("bye")) break;
            else if (sentence[0].equals("done")) {
                int index = Integer.parseInt(sentence[1] ) -1;
                if (size_ >= index) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[✓] " + myList[index].describe());
                    myList[index].done(true);
                }
            }
            else if (sentence[0].equals("todo") || sentence[0].equals("deadline") || sentence[0].equals("event")) {
                if (sentence[0].equals("todo")) {
                    myList[size_] = new Todo(command.substring(5));
                } else if (sentence[0].equals("deadline")) {
                    int end_by = command.indexOf("/by");
                    String by = command.substring(end_by + 4);
                    myList[size_] = new Deadline(command.substring(9, end_by - 1), by);
                } else if (sentence[0].equals("event")) {
                    int end_at = command.indexOf("/at");
                    String at = command.substring(end_at + 4);
                    myList[size_] = new Event(command.substring(6, end_at - 1), at);
                }
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + myList[size_].toString());
                System.out.println("Now you have "+ Integer.toString(size_ + 1) +" tasks in the list.");
                size_ += 1;
            }
            else {
                System.out.println("Sorry, that is not a registered command for duke :(");
            }
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
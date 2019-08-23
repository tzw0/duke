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
                for (int x = 0; x < size_; x ++) {
                    System.out.print(Integer.toString(x + 1) + ".");
                    System.out.print(myList[x].getStatusIcon());
                    System.out.println(myList[x].describe());
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
            else {
                System.out.println("added: "  + command);
                myList[size_] = new Task(command, false);
                size_ += 1;
            }
            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
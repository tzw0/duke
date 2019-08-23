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
        String[] myList = new String[100];
        int size_ = 0;
        while (true) {
            String command = input.nextLine();
            System.out.println(line);
            if (command.equals("list")) {
                for (int x = 0; x < size_; x ++) {
                    System.out.println(Integer.toString(x + 1) + ". " + myList[x]);
                }
            }
            else if (command.equals("bye")) break;
            else {
                System.out.println("added: "  + command);
                myList[size_] = command;
                size_ += 1;
            }
            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
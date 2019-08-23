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
        String command = input.next();
        String end0 = "bye";
        List myList = new ArrayList();
        while (true) {
            if (command.equals("bye")) break;
            System.out.println(line);
            System.out.println(command);
            System.out.println(line);
            command = input.next();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}

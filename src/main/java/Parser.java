import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public static Command parse(String input) throws DukeException { //input validation
        ArrayList<String> command_list = new ArrayList<String>(Arrays.asList("bye", "list", "find", "delete", "done", "todo", "deadline", "event"));
        String operation;
        String date;
        int index =-1;
        String arg1;
        int command_status = -1;
        if (!input.isBlank()) command_status = command_list.indexOf(input.split(" ")[0]);
        else {
            throw new DukeException("unknown");
        }
        if (command_status > -1) { //keyword 1 is accepted
            String[] operation_list = input.split(" ");
            operation = operation_list[0];
            if ((operation.equals("delete") || operation.equals("done"))) {
                if (operation_list.length != 2) throw new DukeException("index error" + input.length());
                try {
                    index = Integer.parseInt(operation_list[1]) -1;
                    if (index < 0) throw new DukeException("index error");
                } catch (NumberFormatException e) {
                    throw new DukeException("index error");
                }
                if (operation.equals("delete")) return new DeleteCommand(index);
                else return new DoneCommand(index); //done
            } else if (operation.equals("find") || operation.equals("todo")) {
                if (operation_list.length == 1) throw new DukeException("arg1 error "+ operation);
                arg1 = input.substring(5);
                if (arg1.isBlank()) throw new DukeException("arg1 error "+ operation);
                arg1.trim();
                if (operation.equals("find")) return new FindCommand(arg1);
                else return new AddCommand("todo", arg1);
            } else if (operation.equals("deadline")) {
                int by_index = input.indexOf(" /by ");
                if (by_index == -1) throw new DukeException("datetime");
                arg1 = input.substring(8, by_index).trim();
                if (arg1.isBlank()) throw new DukeException("arg1 error "+ operation);
                date = input.substring(by_index + 5).trim();
                if (date.isBlank()) throw new DukeException("datetime");
                return new AddCommand("deadline", arg1, date);
            } else if (operation.equals("event")) {
                int at_index = input.indexOf(" /at ");
                if (at_index == -1) throw new DukeException("datetime");
                arg1 = input.substring(5, at_index).trim();
                if (arg1.isBlank()) throw new DukeException("arg1 error "+ operation);
                date = input.substring(at_index + 5).trim();
                if (date.isBlank()) throw new DukeException("datetime");
                return new AddCommand("event", arg1, date);
            } else if (operation.equals("bye")) {
                return new ExitCommand();
            } else if (operation.equals("list")) {
                return new ListCommand();
            }
        }
        throw new DukeException("unknown");
    }
}

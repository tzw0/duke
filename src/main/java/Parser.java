import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    private ArrayList<String> command_list = new ArrayList<String>(Arrays.asList("bye", "list", "find", "delete", "done", "todo", "deadline", "event"));
    private String operation;
    private String date;
    private int index =-1;
    private String arg1;
    public Parser(String input, int size) throws DukeException { //input validation
        int command_status = -1;
        if (!input.isBlank()) command_status = command_list.indexOf(input.split(" ")[0]);
        else {
            throw new DukeException("unknown");
        }
        if (command_status > -1) { //keyword 1 is excepted
            String[] operation_list = input.split(" ");
            operation = operation_list[0];
            if ((operation.equals("delete") || operation.equals("done"))) {
                if (operation_list.length != 2) throw new DukeException("index error" + input.length());
                try {
                    index = Integer.parseInt(operation_list[1]) -1;
                    if (index >= size || index < 0) throw new DukeException("index error");
                } catch (NumberFormatException e) {
                    throw new DukeException("index error");
                }
            } else if (operation.equals("find") || operation.equals("todo")) {
                if (operation_list.length == 1) throw new DukeException("arg1 error "+ operation);
                arg1 = input.substring(5);
                if (arg1.isBlank()) throw new DukeException("arg1 error "+ operation);
                arg1.trim();
            } else if (operation.equals("deadline")) {
                int by_index = input.indexOf(" /by ");
                if (by_index == -1) throw new DukeException("datetime");
                arg1 = input.substring(8, by_index).trim();
                if (arg1.isBlank()) throw new DukeException("arg1 error "+ operation);
                date = input.substring(by_index + 5).trim();
                if (date.isBlank()) throw new DukeException("datetime");
            } else if (operation.equals("event")) {
                int at_index = input.indexOf(" /at ");
                if (at_index == -1) throw new DukeException("datetime");
                arg1 = input.substring(5, at_index).trim();
                if (arg1.isBlank()) throw new DukeException("arg1 error "+ operation);
                date = input.substring(at_index + 5).trim();
                if (date.isBlank()) throw new DukeException("datetime");
            }
        } else {
            throw new DukeException("unknown");
        }
    }
    public boolean command_is(String command) {
        return operation.equals(command);
    }
    public int get_index() {
        return index;
    }
    public String get_arg1() {
        return arg1;
    }
    public String get_date() {
        return date;
    }
}

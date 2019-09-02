import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList extends ArrayList<Task>{
    String sep = "}-}";
    public void save_to(String absolutePath) {
        String output = "";
        for (Task i : this) {
            output += i.task_type() + sep + i.is_Done().toString() + sep + i.describe() + sep + i.extra_() + "\n";
        }
        try (PrintWriter out = new PrintWriter(absolutePath)) {
            out.println(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public TaskList() {
    }
    public TaskList(String file) {
        if (file.isBlank()) {
            return;
        }
        String[] itemlist = file.split("\n");
        int line = 0;
        for (String item: itemlist) {
            line ++;
            String[] attributes = item.split(sep);
            try {
                if (attributes[0].equals("T")) {
                    this.add(new Todo(attributes[2], attributes[1].equals("true")));
                } else if (attributes[0].equals("D")) {
                    this.add(new Deadline(attributes[2], DatetimeCustom.check(attributes[3]), attributes[1].equals("true")));
                } else if (attributes[0].equals("E")) {
                    this.add(new Event(attributes[2], DatetimeCustom.check(attributes[3]), attributes[1].equals("true")));
                } else {
                    System.out.println("☹ OOPS!!! Line " + Integer.toString(line) + " in duke.txt is corrupted" + ", skipping...");
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! Line " + Integer.toString(line) + " in duke.txt is corrupted[" + e + "], skipping...");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Line " + Integer.toString(line) + " in duke.txt is corrupted[" + e + "], skipping...");
            }
        }
    }
    public String print_list() throws DukeException{
        if (this.size()== 0) throw new DukeException("empty list");
        String output ="Here are the tasks in your list:";
        int x = 0;
        Iterator itr=this.iterator();
        while(itr.hasNext()){
            output += "\n" + (Integer.toString(x + 1) + ".");
            output += (itr.next().toString());
            x ++;
        }
        return output;
    }

    public void deleteTask(int index) {
        this.remove(index);
    }
    public void doneTask(int index) {
        this.get(index).done(true);
    }
    public String find(String keyword) {
        int x = 0;
        String output = "Here are the matching tasks in your list:";
        boolean have_result= false;
        for (Task task: this) {
            if (task.toString().indexOf(keyword) != -1) {
                output += "\n" + (Integer.toString(x + 1) + ".");
                output +=  (task.toString());
                x ++;
                have_result = true;
            }
        }
        if (have_result == false) {
            return ("☹ Sorry, no results found");
        }
        return output;
    }
}

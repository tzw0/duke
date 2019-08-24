import java.util.ArrayList;

public class DukeArrayList extends ArrayList<Task>{
    String sep = "}-}";
    public String toFile() {
        String output = "";
        for (Task i : this) {
            output += i.task_type() + sep + i.is_Done().toString() + sep + i.describe() + sep + i.extra_() + "\n";
        }
        return output;
    }
    public void fromFile(String file) {
        String[] itemlist = file.split("\n");
        int line = 0;
        for (String item: itemlist) {
            line ++;
            String[] attributes = item.split(sep);
            try {
                if (attributes[0].equals("T")) {
                    this.add(new Todo(attributes[2], attributes[1].equals("true")));
                } else if (attributes[0].equals("D")) {
                    this.add(new Deadline(attributes[2], attributes[3], attributes[1].equals("true")));
                } else if (attributes[0].equals("E")) {
                    this.add(new Event(attributes[2], attributes[3], attributes[1].equals("true")));
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
}

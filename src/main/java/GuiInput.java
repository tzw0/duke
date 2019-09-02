import java.io.*;
import java.text.ParseException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class GuiInput {
    public static String get_input(String input_gui){
        String output_gui = "";
//        String directory = System.getProperty("user.home");
//        directory += "\\documents\\duke\\data";
//        String savefile = "duke.txt";
//        String absolutePath = directory + File.separator + savefile;
//        DukeArrayList myList = new DukeArrayList();
//        String file_content = "";
//        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
//            String line_X;
//            while ((line_X = br.readLine()) != null) {
//                file_content += line_X + "\n";
//            }
//        } catch (IOException e) {
//            output_gui += "duke.txt file not found, creating file...\n\n";
//        }
//        if (!file_content.isBlank()) myList.fromFile(file_content);
//        int size_ = myList.size();
//        myList.save_to(absolutePath);
//        Boolean no_error = true;
//        String command = input_gui;
//        if (command.isBlank()) {
//            output_gui += ("OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//
//        String sentence[] = command.split(" ");
//        if (command.equals("list")) {
//            if (size_==0) {
//                output_gui += ("OOPS!!! You have no tasks in your list");
//            }
//            output_gui += ("Here are the tasks in your list:") + "\n";
//            int x = 0;
//            Iterator itr=myList.iterator();
//            while(itr.hasNext()){
//                output_gui += (Integer.toString(x + 1) + ".");
//                output_gui += (itr.next().toString()) + "\n";
//                x ++;
//            }
//        }
//        else if (command.equals("bye")) {
//            output_gui += ("Bye. Hope to see you again soon!");
//            System.out.println("Bye. Hope to see you again soon!");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//            exit(0);
//        }
//        else if (sentence[0].equals("done") || sentence[0].equals("delete")) {
//            int index = -1;
//            try {
//                index = Integer.parseInt(sentence[1]) -1;
//            } catch (StringIndexOutOfBoundsException e) {
//                output_gui +=("OOPS!!! No tasks index specified\n");
//            } catch (NumberFormatException e) {
//                output_gui +=("OOPS!!! Please enter the index number you want to mark as done\n");
//            } catch (ArrayIndexOutOfBoundsException e) {
//                output_gui +=("OOPS!!! No tasks index specified\n");
//            }
//            if (size_ > index && index >= 0) {
//                if (sentence[0].equals("done")) {
//                    output_gui +=("Nice! I've marked this task as done:") + "\n";
//                    output_gui +=("[Done] " + myList.get(index).describe());
//                    myList.get(index).done(true);
//                }
//                else if (sentence[0].equals("delete")) {
//                    output_gui +=("Noted. I've removed this task:") + "\n";
//                    output_gui +=("\t" + myList.get(index).toString()) + "\n";
//                    myList.remove(index);
//                    size_ = myList.size();
//                    output_gui +=("Now you have " + Integer.toString(size_) + " tasks in the list.");
//                }
//            }
//            else {
//                output_gui +=("There are no such tasks with this index, you have "+ Integer.toString(size_ ) + " task(s) in the list.");
//            }
//        }
//        else if (sentence[0].equals("find")) {
//            try {
//                String keyword = sentence[1];
//                int x = 0;
//                boolean have_result= false;
//                if (keyword.isBlank()) throw new ArrayIndexOutOfBoundsException();
//                output_gui +=("Here are the matching tasks in your list:") +"\n";
//                for (Task task: myList) {
//                    if (task.toString().indexOf(keyword) != -1) {
//                        output_gui +=(Integer.toString(x + 1) + ".");
//                        output_gui += (task.toString()) + "\n";
//                        x ++;
//                        have_result = true;
//                    }
//                }
//                if (have_result == false) {
//                    output_gui +=("Sorry, no results found");
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//                output_gui +=("OOPS!!! Keyword is empty or blank");
//            }
//        }
//        else if (sentence[0].equals("todo") || sentence[0].equals("deadline") || sentence[0].equals("event")) {
//            if (sentence[0].equals("todo")) {
//                try {
//                    myList.add(new Todo(command.substring(5)));
//                } catch (DukeException e) {
//                    no_error = false;
//                    output_gui +=("OOPS!!! The description of a todo cannot be empty.");
//                } catch (StringIndexOutOfBoundsException e) {
//                    no_error = false;
//                    output_gui +=("OOPS!!! The description of a todo cannot be empty.");
//                }
//            } else if (sentence[0].equals("deadline")) {
//                try {
//                    Integer end_by = command.indexOf(" /by ");
//                    String by = command.substring(end_by + 5);
//                    if (end_by.equals(-1)) {
//                        throw new DukeException("blank by");
//                    }
//                    myList.add(new Deadline(command.substring(9, (end_by >= 0 ? end_by : command.length())), DatetimeCustom.check(by)));
//                } catch (DukeException e) {
//                    no_error = false;
//                    if (e.equals("empty task"))
//                        output_gui +=("OOPS!!! The description of a deadline cannot be empty.");
//                    else {
//                        output_gui +=(e.response());
//                    }
//                } catch (StringIndexOutOfBoundsException e) {
//                    no_error = false;
//                    output_gui +=("OOPS!!! The description of a deadline cannot be empty.");
//                } catch (ParseException e) {
//                    output_gui +=("OOPS!!! Please ensure you have entered a valid date time (eg: mm should not exceed 12)");
//                }
//            } else if (sentence[0].equals("event")) {
//                try {
//                    Integer end_at = command.indexOf(" /at ");
//                    String at = command.substring(end_at + 5);
//                    if (end_at.equals(-1)) {
//                        throw new DukeException("blank at");
//                    }
//                    myList.add(new Event(command.substring(6, end_at >= 0 ? end_at: command.length()), DatetimeCustom.check(at)));
//                } catch (DukeException e) {
//                    no_error = false;
//                    if (e.equals("empty task"))
//                        output_gui +=("OOPS!!! The description of an event cannot be empty.");
//                    else
//                        output_gui +=(e.response());
//                } catch (StringIndexOutOfBoundsException e) {
//                    no_error = false;
//                    output_gui +=("OOPS!!! The description of an event cannot be empty.");
//                }
//            }
//            if (no_error) {
//                output_gui += ("Got it. I've added this task:") + "\n";
//                output_gui += ("\t" + myList.get(size_).toString()) + "\n";
//                output_gui += ("Now you have " + Integer.toString(size_ + 1) + " tasks in the list.");
//                size_ = myList.size();
//            }
//        }
//        else {
//            output_gui += (" OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//        myList.save_to(absolutePath);
        return output_gui;
    }
}

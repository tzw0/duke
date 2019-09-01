import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) throws ParseException {
        String directory = System.getProperty("user.home");
        directory += "\\documents\\duke\\data";
        String savefile = "duke.txt";
        String absolutePath = directory + File.separator + savefile;
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
        DukeArrayList myList = new DukeArrayList();
        String file_content = "";
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
            String line_X;
            while ((line_X = br.readLine()) != null) {
                file_content += line_X + "\n";
            }
        } catch (IOException e) {
            System.out.println("duke.txt file not found, creating file...");
        }
        if (!file_content.isBlank()) myList.fromFile(file_content);
        int size_ = myList.size();
        while (true) {
            try (PrintWriter out = new PrintWriter(absolutePath)) {
                out.println(myList.toFile());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
            else if (sentence[0].equals("find")) {
                try {
                    String keyword = sentence[1];
                    int x = 0;
                    boolean have_result= false;
                    if (keyword.isBlank()) throw new ArrayIndexOutOfBoundsException();
                    System.out.println("Here are the matching tasks in your list:");
                    for (Task task: myList) {
                        if (task.toString().indexOf(keyword) != -1) {
                            System.out.print(Integer.toString(x + 1) + ".");
                            System.out.println(task.toString());
                            x ++;
                            have_result = true;
                        }
                    }
                    if (have_result == false) {
                        System.out.println("☹ Sorry, no results found");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Keyword is empty or blank");
                }
            }
            else if (sentence[0].equals("todo") || sentence[0].equals("deadline") || sentence[0].equals("event")) {
                if (sentence[0].equals("todo")) {
                    try {
                        myList.add(new Todo(command.substring(5)));
                    } catch (DukeException e) {
                        no_error = false;
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
                        myList.add(new Deadline(command.substring(9, (end_by >= 0 ? end_by : command.length())), DatetimeCustom.check(by)));
                    } catch (DukeException e) {
                        no_error = false;
                        if (e.equals("empty task"))
                            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        else {
                            System.out.println(e.response());
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        no_error = false;
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } catch (ParseException e) {
                        System.out.println("☹ OOPS!!! Please ensure you have entered a valid date time (eg: mm should not exceed 12)");
                    }
                } else if (sentence[0].equals("event")) {
                    try {
                        Integer end_at = command.indexOf(" /at ");
                        String at = command.substring(end_at + 5);
                        if (end_at.equals(-1)) {
                            throw new DukeException("blank at");
                        }
                        myList.add(new Event(command.substring(6, end_at >= 0 ? end_at: command.length()), DatetimeCustom.check(at)));
                    } catch (DukeException e) {
                        no_error = false;
                        if (e.equals("empty task"))
                            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        else
                            System.out.println(e.response());
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

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        // more code to be added here later
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText.getText(), new ImageView(user).getImage()),
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(duke).getImage())
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return GuiInput.get_input(input);
    }

}
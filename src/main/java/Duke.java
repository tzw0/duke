import java.io.*;
import java.text.ParseException;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String file) {
        String directory = System.getProperty("user.home");
        directory += "\\documents\\duke\\data";
        String savefile = file;
        String absolutePath = directory + File.separator + savefile;
        storage = new Storage(absolutePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    public String run() {
        ui.print_welcome();
        Scanner input = new Scanner(System.in);
        while (true) {
            cycle(input.nextLine());
        }
    }

    public String cycle(String command) {
        String output = "";
        int size_ = tasks.size();
        tasks.save_to(storage.get_file_path());
        Boolean no_error = true;
        ui.print_line();
        Parser parser;
        try {
            parser = new Parser(command, size_);
        } catch (DukeException e) {
            output += ui.print_this(e.response());
            ui.print_line();
            return output;
        }
        if (parser.command_is("list")) {
            try {
                output += ui.print_this(tasks.print_list());
            } catch (DukeException e) {
                output += ui.showNoTasksError();
                output += ui.print_line();
                return output;
            }
        }
        else if (parser.command_is("bye")) output += ui.close();
        else if (parser.command_is("done")) {
            output += ui.showTaskDone(tasks.get(parser.get_index()).describe());
            tasks.doneTask(parser.get_index());
        }
        else if (parser.command_is("delete")) {
            output += ui.showTaskDelete(tasks.get(parser.get_index()).toString(),size_ -1);
            tasks.deleteTask(parser.get_index());
        }
        else if (parser.command_is("find")) {
            output += ui.print_this(tasks.find(parser.get_arg1()));
        }
        else if (parser.command_is("todo")) {
            try {
                tasks.add(new Todo(parser.get_arg1()));
            } catch (DukeException e) {
                no_error = false;
                output += ui.showEmptyTaskError("todo");
            }
        } else if (parser.command_is("deadline")) {
            try {
                tasks.add(new Deadline(parser.get_arg1(), parser.get_date(), false));
            } catch (DukeException e) {
                no_error = false;
                if (e.equals("empty task"))
                    output += ui.showEmptyTaskError("deadline");
                else {
                    output += ui.showDatetimeError("deadline");
                }
            }
        } else if (parser.command_is("event")) {
            try {
                tasks.add(new Event(parser.get_arg1(), parser.get_date(), false));

            } catch (DukeException e) {
                no_error = false;
                if (e.equals("empty task"))
                    output += ui.showEmptyTaskError("event");
                else
                    output += ui.showDatetimeError("event");
            }
        }
        if (parser.command_is("event") || parser.command_is("todo") || parser.command_is("deadline") ) {
            if (no_error) output += ui.showTaskAdded(tasks.get(size_).toString(), size_ + 1);
        }
        output += ui.print_line();
        return output;
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

//    @Override
    public void start(Stage stage) {
        //Step 1. Setting up reqoutput += uired components

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
        String unhappyface = "☹ ";
        String tick = "✓";
        String cross = "✗";
        input.replaceAll(tick, "ksadsd");
        return cycle(input);
    }
}
package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    //Add a label to your stage to display modifications of contents of textfield
    Label displayString = new Label("");

    //Used to allow the user to enter text
    TextField enteredText = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Components of GUI
        //Add a label to your stage to tell the user what to enter in textfield
        Label enterPhrase = new Label("Please enter a phrase: ");


        //Buttons that will have various actions
        Button echo = new Button("Display Unchanged");
        echo.setOnAction(new ButtonEchoClickHandler());

        Button upperCase = new Button("Convert to Uppercase");
        upperCase.setOnAction(new ButtonUpperClickHandler());

        Button lowerCase = new Button("Convert to Lowercase");
        lowerCase.setOnAction(new ButtonLowerClickHandler());

        Button calcLength = new Button("Calculate Length of String");
        calcLength.setOnAction(new ButtonLengthClickHandler());

        //Create a VBox is used to layout the components in a vertical row
        VBox layout = new VBox(5);

        //Add all the components in the order you want them to appear
        layout.getChildren().addAll(enterPhrase, enteredText, echo,
                upperCase, lowerCase, calcLength, displayString );


        primaryStage.setTitle("String Transition Program");
        primaryStage.setScene(new Scene(layout, 300, 275));
        primaryStage.show();
    }


    /*
    Event Handler class for upperCase button
    */
    class ButtonUpperClickHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
            displayString.setText(enteredText.getText().toUpperCase());
        }
    }

    /*
    Event Handler class for lowerCase button
    */
    class ButtonLowerClickHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
            displayString.setText(enteredText.getText().toLowerCase());
        }
    }

    /*
    Event Handler class for calcLength button
    */
    class ButtonLengthClickHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
            displayString.setText(Integer.toString(enteredText.getText().length()));
        }
    }

    /*
    Event Handler class for echo button
    */
    class ButtonEchoClickHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
            displayString.setText(enteredText.getText());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

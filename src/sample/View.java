package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class View extends Application implements EventHandler<ActionEvent> {
    private Controller controller;
    private Frage question;
    private Label frage;
    private Button button;
    private Label timeLabel;
    private Integer seconds;
    private boolean started;
    private boolean correct;
    private Button score;
    private Button next;


    public View() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        seconds = 3600;

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Fragebogen_Test");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, 1000, 1000);

        //Start Pane
        Label label1 = new Label("Fuhrerscheinfragebogen");
        label1.setMinSize(60, 60);
        label1.setStyle("-fx-font-size:20");
        Button start = new Button("START");
        start.setMinSize(60, 60);
        start.setStyle("-fx-font-size:40");
        start.setOnAction(e -> {
            primaryStage.setScene(scene);
            doTime();
        });
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, start);
        layout1.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout1, 800, 600);
        primaryStage.setScene(scene1);
        primaryStage.show();

        question = controller.getNextQuestion();
        timeLabel = new Label();

        /**
         * Buttons to be introduced
         */
        frage = new Label(question.getID() + ". " + question.getFragetext());
        Image image;
        image = new Image(new FileInputStream(question.getLinktophoto()));
        ImageView imageView = new ImageView(image);
        imageView.setX(200);
        imageView.setY(200);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        CheckBox cb1 = new CheckBox(question.getAntwort1());
        CheckBox cb2 = new CheckBox(question.getAntwort2());
        CheckBox cb3 = new CheckBox(question.getAntwort3());
        CheckBox cb4 = new CheckBox(question.getAntwort4());
        next = new Button("NEXT");
        score = new Button("CORRECT: " + controller.getRightAnswers() + "\nWRONG: " + controller.getWrongAnswers());

        /**
         * Buttons size and fonts
         */

        cb1.setStyle(
                "-fx-border-color: lightblue; "
                        + "-fx-font-size: 20;"
                        + "-fx-border-insets: -5; "
                        + "-fx-border-radius: 5;"
                        + "-fx-border-style: dotted;"
                        + "-fx-border-width: 2;"
        );
        cb2.setStyle(
                "-fx-border-color: lightblue; "
                        + "-fx-font-size: 20;"
                        + "-fx-border-insets: -5; "
                        + "-fx-border-radius: 5;"
                        + "-fx-border-style: dotted;"
                        + "-fx-border-width: 2;"
        );
        cb3.setStyle(
                "-fx-border-color: lightblue; "
                        + "-fx-font-size: 20;"
                        + "-fx-border-insets: -5; "
                        + "-fx-border-radius: 5;"
                        + "-fx-border-style: dotted;"
                        + "-fx-border-width: 2;"
        );
        cb4.setStyle(
                "-fx-border-color: lightblue; "
                        + "-fx-font-size: 20;"
                        + "-fx-border-insets: -5; "
                        + "-fx-border-radius: 5;"
                        + "-fx-border-style: dotted;"
                        + "-fx-border-width: 2;"
        );
        frage.setMinSize(75, 75);
        next.setMinSize(75, 75);
        timeLabel.setMinSize(60, 60);
        //button1.setStyle("-fx-font-size:40");
        //button2.setStyle("-fx-font-size:40");
        //button3.setStyle("-fx-font-size:40");
        next.setStyle("-fx-font-size:40");
        //button.setStyle("-fx-font-size:20");
        frage.setStyle("-fx-font-size:20");
        timeLabel.setStyle("-fx-font-size:20");

        next.setOnAction(e -> {
            List<String> gegeben = new ArrayList<>();
            if (cb1.isSelected()) {
                gegeben.add("A");
            }
            if (cb2.isSelected()) {
                gegeben.add("B");
            }
            if (cb3.isSelected()) {
                gegeben.add("C");
            }
            if (cb4.isSelected()) {
                gegeben.add("D");
            }
            if (controller.areAnswersOkay(gegeben, question)) {
                controller.setCorrectQuestion();
            } else {
                controller.setFalseQuestion();
            }
            score.setText("CORRECT: " + controller.getRightAnswers() + "\nWRONG: " + controller.getWrongAnswers());
            question = controller.getNextQuestion();
            if (question == null || controller.getWrongAnswers() == 5) {

                primaryStage.setScene(endeFragebogen(primaryStage));
                primaryStage.show();
            }
            frage.setText(question.getID() + ". " + question.getFragetext());
            cb1.setText(question.getAntwort1());
            cb2.setText(question.getAntwort2());
            cb3.setText(question.getAntwort3());
            cb4.setText(question.getAntwort4());
            cb1.setIndeterminate(false);
            cb2.setIndeterminate(false);
            cb3.setIndeterminate(false);
            cb4.setIndeterminate(false);
            Image image2;
            try {
                image2 = new Image(new FileInputStream(question.getLinktophoto()));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            ImageView imageView2 = new ImageView(image);
            imageView2.setX(200);
            imageView2.setY(200);
            imageView2.setFitHeight(100);
            imageView2.setFitWidth(100);
            imageView2.setPreserveRatio(true);

        });

        GridPane gridPane = new GridPane();

        VBox hbox = new VBox(20);
        hbox.setPadding(new Insets(50, 50, 50, 50));
        hbox.getChildren().addAll(imageView, cb1, cb2, cb3, cb4, next, score);
        hbox.setAlignment(Pos.CENTER);

        HBox hbox1 = new HBox(20);
        hbox1.setPadding(new Insets(10, 10, 10, 10));
        hbox1.getChildren().addAll(timeLabel);
        hbox1.setAlignment(Pos.CENTER);

        layout.setTop(hbox1);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(frage, 1, 1);

        layout.setCenter(gridPane);
        layout.setBottom(hbox);
        primaryStage.setTitle("Chestionar auto");
    }

    private void doTime() {
        Timeline time = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                seconds--;
                Integer minutes = seconds / 120;
                Integer leftSeconds = seconds % 60;
                timeLabel.setText("Time: " + minutes.toString() + ":" + leftSeconds.toString());
                if (seconds <= 0) {
                    System.exit(0);
                }
            }
        });
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(frame);
        if (time != null) {
            time.stop();
        }
        time.play();
    }

    public void menu(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == next) {
            correct = true;
        } else {
            correct = false;
        }
    }

    public Scene endeFragebogen(Stage primaryStage) {
        Label label2 = new Label("ENDE");
        Label label = new Label();
        label2.setMinSize(60, 60);
        label2.setStyle("-fx-font-size:20");
        label.setMinSize(60, 60);
        label.setStyle("-fx-font-size:20");
        Button restart = new Button("RESTART");
        restart.setMinSize(60, 60);
        restart.setStyle("-fx-font-size:40");
        restart.setOnAction(e -> {
            try {
                start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        if (controller.getWrongAnswers() == 5)
            label.setText("Durchgefallen");
        else
            label.setText("Fuhrerschein genommen!");
        Button finish = new Button("ENDE");
        finish.setMinSize(60, 60);
        finish.setStyle("-fx-font-size:40");
        finish.setOnAction(e -> {
            System.exit(0);
        });
        VBox layout2 = new VBox(20);
        Button score2 = new Button();
        score.setMinSize(60, 60);
        score.setStyle("-fx-font-size:15");
        layout2.getChildren().addAll(label2, label, restart, finish, score);
        layout2.setAlignment(Pos.CENTER);
        Scene scene2 = new Scene(layout2, 800, 600);
        return scene2;
    }

}

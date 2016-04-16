package game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Christian on 29-3-2016.
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);}

    Pane playfieldLayer;

    @Override
    public void start(Stage stage) throws Exception {
        final Stage window = stage;
        window.setTitle("Game Title");

        playfieldLayer = new Pane();

        //Buttons
        Button button1 = new Button("Start");
        button1.setMinWidth(150);
        button1.setId("StartButton");
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, new StartEventHandler());

        Button button2 = new Button("Stop");
        button2.setMinWidth(150);
        button2.setId("StopButton");
        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, new StopEventHandler());

        Button button3 = new Button("Exit");
        button3.setMinWidth(150);
        button3.setId("ExitButton");
        button3.addEventHandler(MouseEvent.MOUSE_CLICKED, new ExitEventHandler());

        Label label1 = new Label("Score : ");
        label1.setMinWidth(100);
        Label label2 = new Label("Accuracy : % ");
        label2.setMinWidth(100);

        //Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15, 15, 15, 15));
        grid.setVgap(10);
        grid.setHgap(5);
        grid.setConstraints(button1, 0, 1);
        grid.setConstraints(button2, 0, 2);
        grid.setConstraints(button3, 0, 11);
        grid.setConstraints(label1, 1, 0);
        grid.setConstraints(label2, 15, 0);

        //Images
        ImageView jawa = new ImageView("Jawa.jpg");
        jawa.setFitWidth(150);
        jawa.setFitHeight(150);
        grid.add(jawa, 0, 6);

        //ImageView tat = new ImageView("Tatooine.jpg");
        //tat.setFitHeight(750);
        //tat.setFitWidth(750);
        //grid.add(tat, 1, 1, 31, 31);

        Scene scene = new Scene(grid, 930, 810);
        window.setScene(scene);
        scene.setCursor(Cursor.CROSSHAIR);

        //Get Mouse position on click
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                event.getSceneX();
                event.getSceneY();
                System.out.println(event.getSceneY() + " " + event.getSceneX());
            }
        });

        grid.getChildren().addAll(playfieldLayer, button1, button2, button3, label1, label2);

        window.show();

    }
        //Hit or miss?

        //Timeline
    private class StartEventHandler implements EventHandler<Event> {
            public void handle(Event evt) {
                boolean confirmStart = ConfirmStart.display("Start game", "happy hunting!");
                if (confirmStart) {
                    TimeLine.timeline.play();
                    TimeLine.timer.start();

                }
            }
        }

    private class StopEventHandler implements EventHandler<Event> {
        public void handle(Event evt) {

        }
    }

    private class ExitEventHandler implements EventHandler<Event> {
        public void handle(Event evt) {
            boolean answer = ConfirmBox.display("Confirm exit", "Do you really want to quite?");
            if(answer){
                Platform.exit(); }
            }
    }
}



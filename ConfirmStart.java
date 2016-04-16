package game;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Christian on 16-4-2016.
 */
public class ConfirmStart {    static boolean confirmStart;

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        //Block other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirm");
        window.setMinWidth(100);

        Label label = new Label();
        label.setText("Start Game!");

        //Buttons
        Button button1 = new Button("Go Hunting");
        button1.setMinWidth(250);
        button1.setId("YesButton");
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, new YesEventHandler());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, button1);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return confirmStart;
    }
    private static class YesEventHandler implements EventHandler<Event> {
        public void handle(Event evt) {
            confirmStart = true;
            TimeLine.timeline.play();
            TimeLine.timer.start();
        }
    }
}

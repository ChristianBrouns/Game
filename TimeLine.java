package game;

import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


/**
 * Created by Christian on 15-4-2016.
 */
public class TimeLine extends Application {

    //main timeline
    public static Timeline timeline;
    public static AnimationTimer timer;

    @Override
    public void start(Stage stage) {
        Group p = new Group();
        Scene scene = new Scene(p);
        stage.setScene(scene);
        stage.setWidth(750);
        stage.setHeight(750);
        p.setTranslateX(300);
        p.setTranslateY(300);


        //create a jawaface with effect
        final ImageView jawaface = new ImageView("JawaFace.png");
        jawaface.setFitWidth(25);
        jawaface.setFitHeight(25);
        jawaface.setEffect(new Lighting());

        //create a layout for jawaface with text inside
        final StackPane stack = new StackPane();
        stack.getChildren().addAll(jawaface);
        stack.setLayoutX(30);
        stack.setLayoutY(30);

        p.getChildren().addAll(stack);
        stage.show();

        //create a timeline for moving the circle
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);

        //You can add a specific action when each frame is started.
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
            }

        };

        //create a keyValue with factory: scaling the circle 2times
        KeyValue keyValueX = new KeyValue(stack.scaleXProperty(), 4);
        KeyValue keyValueY = new KeyValue(stack.scaleYProperty(), 4);

        //create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.millis(800);
        //one can add a specific action when the keyframe is reached
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stack.setTranslateX(java.lang.Math.random() * 400 - 100);
                stack.setTranslateY(java.lang.Math.random() * 400 - 100);
            }
        };

        KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX, keyValueY);

        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        timer.start();


    }

}


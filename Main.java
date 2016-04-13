package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by Christian on 29-3-2016.
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Pane playfieldLayer;

    @Override
    public void start(Stage stage) throws Exception {
        Stage window = stage;
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
        ImageView tat = new ImageView("Tatooine.jpg");
        tat.setFitHeight(750);
        tat.setFitWidth(750);
        grid.add(tat, 1, 1, 31, 31);

        final Sprite enemy = new Sprite();
        enemy.setImage("JawaFace.png");
        enemy.setPosition(400, 240);
        enemy.setVelocity(20, 20);

        final ArrayList<Sprite> enemies = new ArrayList<>();

        //Add to grid
        grid.getChildren().addAll(playfieldLayer, button1, button2, button3, label1, label2);
        Scene scene = new Scene(grid, 930, 810);
        window.setScene(scene);
        scene.setCursor(Cursor.CROSSHAIR);

        //Get Mouse position on click
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    public void handle(MouseEvent event) {
                                        event.getSceneX();
                                        event.getSceneY();
                                        Sprite.getSceneX();
                                        Sprite.getSceneY();
                                        return (Double (event.getSceneX()));
                                        System.out.println(event.getSceneY());}

                                        //Hit or miss?

                                            int score = 1;
                                            int shotsFired = 1;
                                            int accuracy = score / shotsFired * 100;
                                    public boolean checkCollision() {
                                        if (Sprite.getSceneY() == event.getSceneY() && Sprite.getSceneX() == event.getSceneX()) {
                                            score++;
                                            shotsFired++;
                                            return true;
                                        } else {
                                            shotsFired++;
                                            return false;
                                        }

            });


        final long startNanoTime = System.nanoTime();

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                enemy.setVelocity(20, 20);
                enemy.setDirection();
                enemy.setPosition(200, 220);
                switch (Sprite.setDirection()) {
                    case 1:
                        enemy.addVelocity(-200, 0);
                        break;
                    case 2:
                        enemy.addVelocity(200, 0);
                        break;
                    case 3:
                        enemy.addVelocity(0, -200);
                        break;
                    case 4:
                        enemy.addVelocity(0, 200);
                        break;
                    default:
                        enemy.addVelocity(0, 0);
                        break;

                    reset();

                    checkCollision();

                    for (Sprite enemy : enemies)


                }
            }

            //reset method for when Jawa is killed
            public void reset() {
                //the same initial setup as before
                final Sprite enemy = new Sprite();
                enemy.setImage("JawaFace.png");
                enemy.setPosition(400, 240);
                enemy.setVelocity(20, 20);

            window.show());
        }
    }
        }
    }
    private class StartEventHandler implements EventHandler<Event> {
        public void handle(Event evt) {
            timer.start();
        }
    }

    private class StopEventHandler implements EventHandler<Event> {
        public void handle(Event evt) {
            timer.stop();
        }
    }

    private class ExitEventHandler implements EventHandler<Event> {
        public void handle(Event evt) {
            boolean answer = ConfirmBox.display("Confirm exit", "Do you really want to quite?");
            if(answer) {
                Platform.exit();
            }
        }}}



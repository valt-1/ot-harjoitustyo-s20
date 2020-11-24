package spaceinvaders.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import spaceinvaders.domain.Game;

public class SpaceInvadersUi extends Application {

    private Game game;

    @Override
    public void init() {
        game = new Game(800, 800);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(game.getSizeX(), game.getSizeY());

        Text score = new Text(10, 20, "Score: 0");
        pane.getChildren().add(score);

        pane.getChildren().add(game.getLaserGunShape());

        Scene scene = new Scene(pane);

        Map<KeyCode, Boolean> pressedKeys = new HashMap();
        scene.setOnKeyPressed(event ->
                                pressedKeys.put(event.getCode(), Boolean.TRUE));
        scene.setOnKeyReleased(event ->
                                pressedKeys.put(event.getCode(), Boolean.FALSE));

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                game.update();
                score.setText("Score: " + game.getScore());

                if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
                    game.moveGunLeft();
                }

                if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
                    game.moveGunRight();
                }

                if (pressedKeys.getOrDefault(KeyCode.SPACE, Boolean.FALSE)) {
                    game.shoot();
                }

                pane.getChildren().removeAll(game.getRemovedShapes());

                pane.getChildren().removeAll(game.getAliveShapes());
                pane.getChildren().addAll(game.getAliveShapes());

            }
        }.start();

        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

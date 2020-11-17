package spaceinvaders.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import spaceinvaders.domain.Alien;
import spaceinvaders.domain.Shot;
import spaceinvaders.domain.Game;
import spaceinvaders.domain.MovingCharacter;

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

        pane.getChildren().add(game.getLaserGun().getShape());

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

                if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
                    game.moveLeft();
                }

                if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
                    game.moveRight();
                }

                if (pressedKeys.getOrDefault(KeyCode.SPACE, Boolean.FALSE)) {
                    game.shoot();
                }

                for (MovingCharacter movingChar : game.getRemoved()) {
                    pane.getChildren().remove(movingChar.getShape());
                }

                for (Shot shot : game.getShots()) {
                    pane.getChildren().remove(shot.getShape());
                    pane.getChildren().add(shot.getShape());
                }

                for (Alien alien : game.getAliens()) {
                    pane.getChildren().remove(alien.getShape());
                    pane.getChildren().add(alien.getShape());
                }
            }
        }.start();;

        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

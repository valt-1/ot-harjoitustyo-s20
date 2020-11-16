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

        pane.getChildren().add(game.getLaserGun().getShape());
        for (Alien alien : game.getAliens()) {
            pane.getChildren().add(alien.getShape());
        }

        Scene scene = new Scene(pane);

        Map<KeyCode, Boolean> pressedKeys = new HashMap();
        scene.setOnKeyPressed(event ->
                                pressedKeys.put(event.getCode(), Boolean.TRUE));
        scene.setOnKeyReleased(event ->
                                pressedKeys.put(event.getCode(), Boolean.FALSE));

        new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
                    game.getLaserGun().moveLeft();
                }

                if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
                    game.getLaserGun().moveRight();
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

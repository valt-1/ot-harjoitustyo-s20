package spaceinvaders.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import spaceinvaders.dao.FileHiScoreDao;
import spaceinvaders.dao.HiScoreDao;
import spaceinvaders.domain.Game;

public class SpaceInvadersUi extends Application {

    private HiScoreDao hiScoreDao;
    private Game game;

    @Override
    public void init() throws IOException {
        hiScoreDao = new FileHiScoreDao("hiscore.txt");
        game = new Game(hiScoreDao, 800, 800);
    }

    @Override
    public void start(Stage stage) {
        Pane mainPane = new Pane();
        mainPane.setPrefSize(game.getSizeX(), game.getSizeY() + 20);

        Pane gamePane = new Pane();
        gamePane.setPrefSize(game.getSizeX(), game.getSizeY());
        mainPane.getChildren().add(gamePane);

        HBox textBox = new HBox(20);
        textBox.setTranslateX(20);
        textBox.setTranslateY(10);
        Label score = new Label("Score: 0");
        score.setMinWidth(100);
        Label hiScore = new Label("HiScore: " + game.getHiScore());
        hiScore.setMinWidth(100);
        Label lives = new Label("Lives: ");
        lives.setMinWidth(420);

        Button restart = new Button("Restart");
        restart.setFocusTraversable(false);
        restart.setOnMouseClicked(e -> {
            try {
                game.saveHiScore();
            } catch (Exception ex) {
                Logger.getLogger(SpaceInvadersUi.class.getName()).log(Level.SEVERE, null, ex);
            }

            gamePane.getChildren().clear();
            game = new Game(hiScoreDao, 800, 800);
            gamePane.getChildren().add(game.getLaserGunShape());
            score.setText("Score: 0");
            hiScore.setText("HiScore: " + game.getHiScore());
        });

        textBox.getChildren().addAll(score, hiScore, lives, restart);
        mainPane.getChildren().add(textBox);

        gamePane.getChildren().add(game.getLaserGunShape());

        Scene scene = new Scene(mainPane);

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

                gamePane.getChildren().removeAll(game.getRemovedShapes());

                gamePane.getChildren().removeAll(game.getAliveShapes());
                gamePane.getChildren().addAll(game.getAliveShapes());

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

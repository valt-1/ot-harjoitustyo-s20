package spaceinvaders.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import spaceinvaders.dao.FileHiScoreDao;
import spaceinvaders.dao.HiScoreDao;
import spaceinvaders.domain.Game;

public class SpaceInvadersUi extends Application {

    private HiScoreDao hiScoreDao;
    private double size;
    private double speed;
    private Game game;

    @Override
    public void init() throws IOException {
        hiScoreDao = new FileHiScoreDao("hiscore.txt");
        size = 800;
        speed = 0.2;
        game = new Game(hiScoreDao, size, speed);
    }

    @Override
    public void start(Stage stage) {
        // Start scene
        StackPane startPane = new StackPane();
        startPane.setPrefSize(size, size + 20);
        Scene startScene = new Scene(startPane);

        // Game scene
        Pane mainPane = new Pane();
        mainPane.setPrefSize(size, size + 20);
        HBox textBox = new HBox(20);
        textBox.setTranslateX(20);
        textBox.setTranslateY(10);
        Label score = new Label("Score: 0");
        score.setMinWidth(100);
        Label hiScore = new Label("HiScore: " + game.getHiScore());
        hiScore.setMinWidth(100);
        Label lives = new Label("Lives: ");
        lives.setMinWidth(420);
        textBox.getChildren().addAll(score, hiScore, lives);
        mainPane.getChildren().add(textBox);

        Pane gamePane = new Pane();
        gamePane.setPrefSize(size, size);
        mainPane.getChildren().add(gamePane);

        Scene gameScene = new Scene(mainPane);

        // Start scene -
        // buttons for quitting the application and starting a new game
        Button quit = new Button("Quit");
        quit.setPrefWidth(100);
        startPane.setAlignment(quit, Pos.TOP_RIGHT);
        quit.setFocusTraversable(false);
        quit.setOnAction(e -> Platform.exit());
        startPane.getChildren().add(quit);

        Button start = new Button("Start Game");
        start.setPrefSize(150, 40);
        startPane.setAlignment(start, Pos.CENTER);
        start.setOnAction(e -> {
            gamePane.getChildren().clear();
            game = new Game(hiScoreDao, size, speed);
            stage.setScene(gameScene);

            hiScore.setText("HiScore: " + game.getHiScore());
            gamePane.getChildren().add(game.getLaserGunShape());

            Map<KeyCode, Boolean> pressedKeys = new HashMap();
            gameScene.setOnKeyPressed(event
                    -> pressedKeys.put(event.getCode(), Boolean.TRUE));
            gameScene.setOnKeyReleased(event
                    -> pressedKeys.put(event.getCode(), Boolean.FALSE));

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
        });
        startPane.getChildren().add(start);

        // Game scene - button for quitting game
        Button quitGame = new Button("Quit game");
        quitGame.setPrefWidth(100);
        quitGame.setTranslateX(size - 100);
        quitGame.setFocusTraversable(false);
        quitGame.setOnMouseClicked(e -> {
            try {
                game.saveHiScore();
            } catch (Exception ex) {
                Logger.getLogger(SpaceInvadersUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.setScene(startScene);
        });
        mainPane.getChildren().add(quitGame);

        // Set title and initial view
        stage.setTitle("Space Invaders");
        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

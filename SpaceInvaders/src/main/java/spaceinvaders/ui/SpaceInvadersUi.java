package spaceinvaders.ui;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import spaceinvaders.dao.FileHiScoreDao;
import spaceinvaders.dao.HiScoreDao;
import spaceinvaders.logics.Game;

public class SpaceInvadersUi extends Application {

    private HiScoreDao hiScoreDao;
    private double size;
    private double speed;
    private Game game;
    private boolean exceptionInInit = false;
    private AnimationTimer timer;

    @Override
    public void init() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            String hiScoreFile = properties.getProperty("hiScoreFile");
            double alienSpeed = Double.valueOf(properties.getProperty("speed"));

            hiScoreDao = new FileHiScoreDao(hiScoreFile);
            size = 800;
            speed = alienSpeed;
            game = new Game(hiScoreDao, size, speed);
        } catch (Exception ex) {
            exceptionInInit = true;
        }
    }

    @Override
    public void start(Stage stage) {
        if (exceptionInInit) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Virhe ohjelman alustuksessa.");
            alert.showAndWait();
            Platform.exit();
            return;
        }

        // Start scene
        StackPane startPane = new StackPane();
        startPane.setPrefSize(size, size + 20);
        Label gameInfo = new Label("Shoot the aliens before they invade our planet!");
        gameInfo.setTranslateY(-size / 10);
        gameInfo.setTextAlignment(TextAlignment.CENTER);
        startPane.getChildren().add(gameInfo);
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

        StackPane gameStackPane = new StackPane();
        gameStackPane.setPrefSize(size, size);
        Pane gamePane = new Pane();
        gamePane.setPrefSize(size, size);
        gameStackPane.getChildren().add(gamePane);
        Label gameStatus = new Label("");
        gameStackPane.setAlignment(gameStatus, Pos.CENTER);
        gameStackPane.getChildren().add(gameStatus);
        mainPane.getChildren().add(gameStackPane);

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
            gameStatus.setText("");
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

            timer = new AnimationTimer() {

                private void redrawGame() {
                    gamePane.getChildren().removeAll(game.getRemovedShapes());

                    gamePane.getChildren().removeAll(game.getAliveShapes());
                    gamePane.getChildren().addAll(game.getAliveShapes());
                }

                @Override
                public void handle(long now) {
                    game.update();

                    if (game.isOver()) {
                        this.stop();
                        gameStatus.setText("Game over!");
                    }

                    if (game.isWon()) {
                        this.stop();
                        gameStatus.setText("OMG you saved the world!!! <3");
                    }

                    score.setText("Score: " + game.getScore());
                    String livesText = "Lives: " + game.getLives();
                    if (game.getLives() == 2) {
                        livesText += " - OUCH!!";
                    }
                    if (game.getLives() == 1) {
                        livesText += " - WATCH OUT!!!";
                    }
                    lives.setText(livesText);

                    if (pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE)) {
                        game.moveGunLeft();
                    }

                    if (pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)) {
                        game.moveGunRight();
                    }

                    if (pressedKeys.getOrDefault(KeyCode.SPACE, Boolean.FALSE)) {
                        game.shoot();
                    }

                    redrawGame();
                }
            };
            timer.start();
        });
        startPane.getChildren().add(start);

        // Game scene - button for quitting game
        Button quitGame = new Button("Quit game");
        quitGame.setPrefWidth(100);
        quitGame.setTranslateX(size - 100);
        quitGame.setFocusTraversable(false);
        quitGame.setOnMouseClicked(e -> {
            timer.stop();
            try {
                game.saveHiScore();
            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Virhe pisteiden tallennuksessa");
                alert.showAndWait();
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

package spaceinvaders.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import spaceinvaders.domain.LaserGun;

public class SpaceInvadersUi extends Application {

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(800, 600);
        
        LaserGun laserGun = new LaserGun();
        laserGun.setLocation(400, 590);
        
        pane.getChildren().add(laserGun.getShape());

        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                laserGun.moveLeft();
            }
            
            if (event.getCode() == KeyCode.RIGHT) {
                laserGun.moveRight();
            }
        });
        
        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

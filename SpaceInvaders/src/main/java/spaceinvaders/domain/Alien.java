package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

public class Alien extends MovingCharacter {
    
    private boolean alive;
    
    public Alien(double locationX, double locationY) {
        super (new Polygon(0, 0, 30, 0, 30, -20, 0, -20), locationX, locationY);
        this.alive = true;
    }

}
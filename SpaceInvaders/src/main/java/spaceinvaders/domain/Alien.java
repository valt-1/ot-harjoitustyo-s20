package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

public class Alien extends GameCharacter {
    
    public Alien(double locationX, double locationY) {
        super(new Polygon(0, 0, 30, 0, 30, -20, 0, -20), locationX, locationY);
    }

    public void moveHorizontal(int direction, double speed) {
        this.setLocationX(this.getLocationX() + direction * speed);
    }

    public void moveDown() {
        this.setLocationY(this.getLocationY() + 40);
    }
}

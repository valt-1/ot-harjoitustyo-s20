package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

public class LaserGun extends GameCharacter {
    
    public LaserGun(double locationX, double locationY) {
        super(new Polygon(10, 0, 0, -20, -10, 0), locationX, locationY);
    }

    public void moveLeft() {
        this.setLocationX(this.getLocationX() - 1);
    }
    
    public void moveRight() {
        this.setLocationX(this.getLocationX() + 1);
    }
}

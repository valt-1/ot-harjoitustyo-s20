package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

public class LaserGun extends MovingCharacter {
    
    public LaserGun() {
        super(new Polygon(10, 0, 0, -20, -10, 0), 400, 590);
    }

    public void moveLeft() {
        this.setLocationX(this.getLocationX() - 10);
    }
    
    public void moveRight() {
        this.setLocationX(this.getLocationX() + 10);
    }
}

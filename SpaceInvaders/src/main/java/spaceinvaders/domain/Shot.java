package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

public class Shot extends MovingCharacter {

    public Shot(double locationX, double locationY) {
        super(new Polygon(-2, 0, -2, -10, 2, -10, 2, 0), locationX, locationY);
    }
    
    public void move() {
        this.setLocationY(this.getLocationY() - 3);
    }

}

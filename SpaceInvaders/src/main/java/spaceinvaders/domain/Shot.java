package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

public class Shot extends GameCharacter {

    public Shot(double locationX, double locationY) {
        super(new Polygon(-2, 0, -2, -20, 2, -20, 2, 0), locationX, locationY);
    }
    
    public void moveUp() {
        this.setLocationY(this.getLocationY() - 3);
    }

}

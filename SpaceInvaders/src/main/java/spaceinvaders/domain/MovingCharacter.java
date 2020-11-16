package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

public class MovingCharacter {
    
    private Polygon shape;
    
    public MovingCharacter(Polygon shape, double locationX, double locationY) {
        this.shape = shape;
        this.shape.setTranslateX(locationX);
        this.shape.setTranslateY(locationY);
    }
    
    public Polygon getShape() {
        return this.shape;
    }

    public double getLocationX() {
        return this.shape.getTranslateX();
    }

    public double getLocationY() {
        return this.shape.getTranslateY();
    }

    public void setLocation(double x, double y) {
        this.setLocationX(x);
        this.setLocationY(y);
    }

    public void setLocationX(double x) {
        this.shape.setTranslateX(x);
    }

    public void setLocationY(double y) {
        this.shape.setTranslateY(y);
    }
    
}

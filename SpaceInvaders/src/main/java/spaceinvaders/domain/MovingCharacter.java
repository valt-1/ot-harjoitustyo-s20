package spaceinvaders.domain;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class MovingCharacter {
    
    private Polygon shape;
    private boolean alive;
    
    public MovingCharacter(Polygon shape, double locationX, double locationY) {
        this.shape = shape;
        this.shape.setTranslateX(locationX);
        this.shape.setTranslateY(locationY);
        this.alive = true;
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

    public boolean isAlive() {
        return this.alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean hits(MovingCharacter other) {
        Shape intersect = Shape.intersect(this.getShape(), other.getShape());
        return intersect.getBoundsInLocal().getWidth() != -1;
    }

}

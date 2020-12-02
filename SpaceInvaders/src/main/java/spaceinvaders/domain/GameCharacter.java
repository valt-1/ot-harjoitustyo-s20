package spaceinvaders.domain;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class GameCharacter {
    
    private Polygon shape;
    private boolean alive;
    
    public GameCharacter(Polygon shape, double locationX, double locationY) {
        this.shape = shape;
        this.shape.setTranslateX(locationX);
        this.shape.setTranslateY(locationY);
        this.alive = true;
    }
    
    public Polygon getShape() {
        return shape;
    }

    public double getLocationX() {
        return shape.getTranslateX();
    }

    public double getLocationY() {
        return shape.getTranslateY();
    }

    public void setLocation(double x, double y) {
        this.setLocationX(x);
        this.setLocationY(y);
    }

    public void setLocationX(double x) {
        shape.setTranslateX(x);
    }

    public void setLocationY(double y) {
        shape.setTranslateY(y);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean hits(GameCharacter other) {
        Shape intersect = Shape.intersect(this.getShape(), other.getShape());
        return intersect.getBoundsInLocal().getWidth() != -1;
    }

}
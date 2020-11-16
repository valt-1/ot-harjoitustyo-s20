package spaceinvaders.domain;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class LaserGun {

    private Polygon shape;
    
    public LaserGun() {
        this.shape = new Polygon(10, 0, 0, -20, -10, 0);
    }

    public Shape getShape() {
        return shape;
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
    
    public double getLocationX() {
        return this.shape.getTranslateX();
    }
    
    public double getLocationY() {
        return this.shape.getTranslateY();
    }
    
    public void moveLeft() {
        this.setLocationX(this.getLocationX() - 10);
    }
    
    public void moveRight() {
        this.setLocationX(this.getLocationX() + 10);
    }
}

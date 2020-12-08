package spaceinvaders.domain;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Pelihahmoa kuvaava luokka.
 */
public class GameCharacter {
    
    private Polygon shape;
    private boolean alive;

    /**
     * Luo uuden pelihahmon.
     *
     * @param shape         hahmon muoto
     * @param locationX     hahmon x-koordinaatti
     * @param locationY     hahmon y-koordinaatti
     */
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

    /**
     * Asettaa hahmoa kuvaavalle muodolle kerralla sekä x- että y-koordinaatin.
     *
     * @param x     hahmon uusi x-koordinaatti
     * @param y     hahmon uusi y-koordinaatti
     */
    public void setLocation(double x, double y) {
        this.setLocationX(x);
        this.setLocationY(y);
    }

    /**
     * Asettaa hahmoa kuvaavalle muodolle x-koordinaatin.
     *
     * @param x     hahmon uusi x-koordinaatti
     */
    public void setLocationX(double x) {
        shape.setTranslateX(x);
    }

    /**
     * Asettaa hahmoa kuvaavalle muodolle y-koordinaatin.
     *
     * @param y     hahmon uusi y-koordinaatti
     */
    public void setLocationY(double y) {
        shape.setTranslateY(y);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Tarkistaa osuuko pelihahmo toiseen hahmoon.
     * Osuma tapahtuu, jos hahmoja kuvaavat muodot osuvat toisiinsa.
     *
     * @param other jokin toinen pelin hahmoista
     *
     * @return      true, jos hahmot koskettavat toisiaan
     *              false, jos hahmot eivät kosketa toisiaan
     */
    public boolean hits(GameCharacter other) {
        Shape intersect = Shape.intersect(this.getShape(), other.getShape());
        return intersect.getBoundsInLocal().getWidth() != -1;
    }

}

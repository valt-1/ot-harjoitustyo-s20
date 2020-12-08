package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

/**
 * Pelin lasertykkiä kuvaava luokka.
 */
public class LaserGun extends GameCharacter {
    
    /**
     * Luo uuden lasertykin.
     *
     * @param locationX     hahmon x-koordinaatti
     * @param locationY     hahmon y-koordinaatti
     */
    public LaserGun(double locationX, double locationY) {
        super(new Polygon(10, 0, 0, -20, -10, 0), locationX, locationY);
    }

    /**
     * Liikuttaa hahmoa vasemmalle.
     */
    public void moveLeft() {
        this.setLocationX(this.getLocationX() - 1);
    }

    /**
     * Liikuttaa hahmoa oikealle.
     */
    public void moveRight() {
        this.setLocationX(this.getLocationX() + 1);
    }
}

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
     * @param speed     liikkeen nopeus
     */
    public void moveLeft(double speed) {
        this.setLocationX(this.getLocationX() - speed);
    }

    /**
     * Liikuttaa hahmoa oikealle.
     * @param speed     liikkeen nopeus
     */
    public void moveRight(double speed) {
        this.setLocationX(this.getLocationX() + speed);
    }
}

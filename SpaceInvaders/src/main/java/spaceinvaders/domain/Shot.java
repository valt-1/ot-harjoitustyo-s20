package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

/**
 * Lasertykin ammusta kuvaava luokka.
 */
public class Shot extends GameCharacter {

    /**
     * Luo uuden ammuksen.
     *
     * @param locationX     hahmon x-koordinaatti
     * @param locationY     hahmon y-koordinaatti
     */
    public Shot(double locationX, double locationY) {
        super(new Polygon(-2, 0, -2, -20, 2, -20, 2, 0), locationX, locationY);
    }

    /**
     * Liikuttaa amusta ylöspäin.
     */
    public void moveUp() {
        this.setLocationY(this.getLocationY() - 3);
    }

}

package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

/**
 * Pelin vihollisena toimivaa avaruusoliota kuvaava luokka.
 */
public class Alien extends GameCharacter {

    /**
     * Luo uuden avaruusolion.
     *
     * @param locationX     hahmon x-koordinaatti
     * @param locationY     hahmon y-koordinaatti
     */
    public Alien(double locationX, double locationY) {
        super(new Polygon(0, 0, 30, 0, 30, -20, 0, -20), locationX, locationY);
    }

    /**
     * Liikuttaa hahmoa vaakasuunnassa.
     *
     * @param direction     liikkeen suunta, -1 liikuttaa vasemmalle
     *                      ja 1 oikealle
     *
     * @param speed         liikkeen nopeus
     */
    public void moveHorizontal(int direction, double speed) {
        this.setLocationX(this.getLocationX() + direction * speed);
    }

    /**
     * Liikuttaa hahmoa yhden rivin alaspäin.
     */
    public void moveDown() {
        this.setLocationY(this.getLocationY() + 40);
    }
}

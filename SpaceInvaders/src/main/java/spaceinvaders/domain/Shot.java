package spaceinvaders.domain;

import javafx.scene.shape.Polygon;

/**
 * Lasertykin tai avaruusolion ammusta kuvaava luokka.
 */
public class Shot extends GameCharacter {

    /**
     * Luo uuden ammuksen, joka lähtee annetuista koordinaateista.
     *
     * @param locationX     hahmon x-koordinaatti
     * @param locationY     hahmon y-koordinaatti
     */
    public Shot(double locationX, double locationY) {
        super(new Polygon(-2, 0, -2, -20, 2, -20, 2, 0), locationX, locationY);
    }

    /**
     * Liikuttaa ammusta ylöspäin.
     */
    public void moveUp() {
        this.setLocationY(this.getLocationY() - 3);
    }

    /**
     * Liikuttaa ammusta alaspäin.
     */
    public void moveDown() {
        this.setLocationY(this.getLocationY() + 3);
    }

}

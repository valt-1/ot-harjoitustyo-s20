package spaceinvaders.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private double sizeX;
    private double sizeY;
    private LaserGun laserGun;
    private List<Alien> aliens;

    public Game(double sizeX, double sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.laserGun = new LaserGun(sizeX / 2, sizeY - 10);
        this.aliens = new ArrayList();

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 5; j++) {
                this.aliens.add(new Alien(10 + i * (20 + 30), 60 + j * (20 + 20)));
            }
        }

    }

    public double getSizeX() {
        return this.sizeX;
    }

    public double getSizeY() {
        return this.sizeY;
    }

    public LaserGun getLaserGun() {
        return this.laserGun;
    }

    public List<Alien> getAliens() {
        return this.aliens;
    }

}

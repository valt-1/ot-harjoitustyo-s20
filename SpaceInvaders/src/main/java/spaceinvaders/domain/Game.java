package spaceinvaders.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.shape.Shape;
public class Game {

    private double sizeX;
    private double sizeY;
    private LaserGun laserGun;
    private List<Alien> aliens;
    private List<Shot> shots;
    private List<MovingCharacter> removed;

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

        this.shots = new ArrayList();
        this.removed = new ArrayList();
    }

    public double getSizeX() {
        return this.sizeX;
    }

    public double getSizeY() {
        return this.sizeY;
    }

    public Shape getLaserGunShape() {
        return this.laserGun.getShape();
    }

    public List<Alien> getAliens() {
        return this.aliens;
    }

    public List<Shot> getShots() {
        return this.shots;
    }

    public List<Shape> getAliveShapes() {
        List<Shape> aliveShapes = new ArrayList();

        for (Alien alien : this.aliens) {
            aliveShapes.add(alien.getShape());
        }

        for (Shot shot : this.shots) {
            aliveShapes.add(shot.getShape());
        }

        return aliveShapes;
    }

    public List<Shape> getRemovedShapes() {
        List<Shape> removedShapes = new ArrayList();
        for (MovingCharacter movingChar : this.removed) {
            removedShapes.add(movingChar.getShape());
        }
        return removedShapes;
    }

    public void moveLeft() {
        if (this.laserGun.getLocationX() > 10) {
            this.laserGun.moveLeft();
        }
    }

    public void moveRight() {
        if (this.laserGun.getLocationX() < this.sizeX - 10) {
            this.laserGun.moveRight();
        }
    }

    public void shoot() {
        if (this.shots.size() < 1) {
            double locationX = this.laserGun.getLocationX();
            this.shots.add(new Shot(locationX, this.sizeY - 30));
        }
    }

    public void update() {
        for (Shot shot : this.shots) {
            if (shot.getLocationY() < 0) {
                shot.setAlive(false);
            }

            shot.moveUp();

            for (Alien alien : this.aliens) {
                if (shot.hits(alien)) {
                    alien.setAlive(false);
                    shot.setAlive(false);
                }
            }
        }

        this.removeDead();
    }

    public void removeDead() {
        this.removed = new ArrayList();

        Iterator<Shot> shotIterator = this.shots.iterator();
        while (shotIterator.hasNext()) {
            Shot shot = shotIterator.next();
            if (!shot.isAlive()) {
                this.removed.add(shot);
                shotIterator.remove();
            }
        }

        Iterator<Alien> alienIterator = this.aliens.iterator();
        while (alienIterator.hasNext()) {
            Alien alien = alienIterator.next();
            if (!alien.isAlive()) {
                this.removed.add(alien);
                alienIterator.remove();
            }
        }
    }

}

package spaceinvaders.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.shape.Shape;
import spaceinvaders.dao.HiScoreDao;

public class Game {
    private HiScoreDao hiScoreDao;

    private double sizeX;
    private double sizeY;
    private int score;
    private int hiScore;

    private LaserGun laserGun;

    private double leftAlienX;
    private double rightAlienX;
    private double bottomAlienY;
    private int alienDirection;
    private double speed;
    private List<Alien> aliens;

    private List<Shot> shots;
    private List<GameCharacter> removed;

    public Game(HiScoreDao hiScoreDao, double sizeX, double sizeY) {
        this.hiScoreDao = hiScoreDao;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.score = 0;
        this.hiScore = this.hiScoreDao.getHiScore();
        this.laserGun = new LaserGun(sizeX / 2, sizeY - 10);

        this.leftAlienX = 0;
        this.rightAlienX = 560;
        this.bottomAlienY = 260;
        this.alienDirection = 1;
        this.speed = 0.2;

        this.aliens = new ArrayList();
        for (int i = 0; i < 11; i++) {
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

    public int getScore() {
        return this.score;
    }

    public int getHiScore() {
        return hiScore;
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
        for (GameCharacter movingChar : this.removed) {
            removedShapes.add(movingChar.getShape());
        }
        return removedShapes;
    }

    public void moveGunLeft() {
        if (this.laserGun.getLocationX() > 10) {
            this.laserGun.moveLeft();
        }
    }

    public void moveGunRight() {
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
        this.moveGunShots();
        this.moveAliens();
        this.removeDead();

        if (this.score > this.hiScore) {
            this.hiScore = this.score;
        }
    }

    private void moveGunShots() {
        for (Shot shot : this.shots) {
            if (shot.getLocationY() < 0) {
                shot.setAlive(false);
            }

            shot.moveUp();

            for (Alien alien : this.aliens) {
                if (shot.hits(alien)) {
                    alien.setAlive(false);
                    shot.setAlive(false);
                    this.score += 10;
                }
            }
        }
    }

    private void moveAliens() {
        if (this.rightAlienX > this.sizeX || this.leftAlienX < 0) {
            this.alienDirection = this.alienDirection * -1;
            for (Alien alien : this.aliens) {
                alien.moveDown();
            }
        }

        for (Alien alien : this.aliens) {
            alien.moveHorizontal(alienDirection, speed);
        }
        this.leftAlienX += this.alienDirection * speed;
        this.rightAlienX += this.alienDirection * speed;
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

    public void saveHiScore() throws Exception {
        hiScoreDao.saveScore(this.hiScore);
    }

}

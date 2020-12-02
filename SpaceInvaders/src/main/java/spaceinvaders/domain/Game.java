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
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public int getScore() {
        return score;
    }

    public int getHiScore() {
        return hiScore;
    }

    public Shape getLaserGunShape() {
        return laserGun.getShape();
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public List<Shot> getShots() {
        return shots;
    }

    public List<Shape> getAliveShapes() {
        List<Shape> aliveShapes = new ArrayList();

        for (Alien alien : aliens) {
            aliveShapes.add(alien.getShape());
        }

        for (Shot shot : shots) {
            aliveShapes.add(shot.getShape());
        }

        return aliveShapes;
    }

    public List<Shape> getRemovedShapes() {
        List<Shape> removedShapes = new ArrayList();
        for (GameCharacter gameChar : removed) {
            removedShapes.add(gameChar.getShape());
        }
        return removedShapes;
    }

    public void moveGunLeft() {
        if (laserGun.getLocationX() > 10) {
            laserGun.moveLeft();
        }
    }

    public void moveGunRight() {
        if (laserGun.getLocationX() < sizeX - 10) {
            laserGun.moveRight();
        }
    }

    public void shoot() {
        if (shots.size() < 1) {
            double locationX = laserGun.getLocationX();
            shots.add(new Shot(locationX, sizeY - 30));
        }
    }

    public void update() {
        moveGunShots();
        moveAliens();
        removeDead();

        if (score > hiScore) {
            hiScore = score;
        }
    }

    private void moveGunShots() {
        for (Shot shot : shots) {
            if (shot.getLocationY() < 0) {
                shot.setAlive(false);
            }

            shot.moveUp();

            for (Alien alien : aliens) {
                if (shot.hits(alien)) {
                    alien.setAlive(false);
                    shot.setAlive(false);
                    score += 10;
                }
            }
        }
    }

    private void moveAliens() {
        if (rightAlienX > sizeX || leftAlienX < 0) {
            alienDirection = alienDirection * -1;
            for (Alien alien : aliens) {
                alien.moveDown();
            }
        }

        for (Alien alien : aliens) {
            alien.moveHorizontal(alienDirection, speed);
        }

        leftAlienX += alienDirection * speed;
        rightAlienX += alienDirection * speed;
    }

    public void removeDead() {
        removed = new ArrayList();

        Iterator<Shot> shotIterator = shots.iterator();
        while (shotIterator.hasNext()) {
            Shot shot = shotIterator.next();
            if (!shot.isAlive()) {
                removed.add(shot);
                shotIterator.remove();
            }
        }

        Iterator<Alien> alienIterator = aliens.iterator();
        while (alienIterator.hasNext()) {
            Alien alien = alienIterator.next();
            if (!alien.isAlive()) {
                removed.add(alien);
                alienIterator.remove();
            }
        }
    }

    public void saveHiScore() throws Exception {
        hiScoreDao.saveScore(hiScore);
    }

}

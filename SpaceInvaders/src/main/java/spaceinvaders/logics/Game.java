package spaceinvaders.logics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.shape.Shape;
import spaceinvaders.dao.HiScoreDao;
import spaceinvaders.domain.Alien;
import spaceinvaders.domain.GameCharacter;
import spaceinvaders.domain.LaserGun;
import spaceinvaders.domain.Shot;

/**
 * Luokka vastaa pelilogiikasta.
 */
public class Game {
    private final HiScoreDao hiScoreDao;

    private final double size;
    private int score;
    private int hiScore;
    private boolean over;
    private boolean won;

    private final LaserGun laserGun;
    private double leftAlienX;
    private double rightAlienX;
    private int alienDirection;
    private double speed;
    private List<Alien> aliens;
    private List<Shot> shots;
    private List<GameCharacter> removed;

    /**
     * Luo uuden pelin.
     *
     * @param hiScoreDao    DAO piste-ennätyksen tallentamista varten
     * @param size          peliruudun koko
     * @param speed         pelin vihollisten liikenopeus
     */
    public Game(HiScoreDao hiScoreDao, double size, double speed) {
        this.hiScoreDao = hiScoreDao;
        this.size = size;
        this.score = 0;
        this.hiScore = this.hiScoreDao.getHiScore();
        this.over = false;
        this.won = false;
        this.laserGun = new LaserGun(size / 2, size);

        this.leftAlienX = 0;
        this.rightAlienX = 560;
        this.alienDirection = 1;
        this.speed = speed;

        this.aliens = new ArrayList();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 5; j++) {
                this.aliens.add(new Alien(10 + i * (20 + 30), 60 + j * (20 + 20)));
            }
        }

        this.shots = new ArrayList();
        this.removed = new ArrayList();
    }

    public double getSize() {
        return size;
    }

    public int getScore() {
        return score;
    }

    public int getHiScore() {
        return hiScore;
    }

    public boolean isOver() {
        return over;
    }

    public boolean isWon() {
        return won;
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

    /**
     * Hakee kaikkia pelissä olevia hahmoja vastaavat Shape-oliot
     * graafista käyttöliittymää varten.
     *
     * @return  lista, joka sisältää kaikkia pelissä olevia hahmoja
     *          vastaavat Shape-oliot
     */
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

    /**
     * Hakee kaikkia pelistä poistettuja hahmoja vastaavat Shape-oliot
     * graafista käyttöliittymää varten.
     *
     * @return  lista, joka sisältää kaikkia pelistä poistettuja hahmoja
     *          vastaavat Shape-oliot
     */
    public List<Shape> getRemovedShapes() {
        List<Shape> removedShapes = new ArrayList();
        for (GameCharacter gameChar : removed) {
            removedShapes.add(gameChar.getShape());
        }
        return removedShapes;
    }

    /**
     * Siirtää lasertykkiä vasemmalle, ellei se ole jo
     * aivan peliruudun vasemmassa laidassa.
     */
    public void moveGunLeft() {
        if (laserGun.getLocationX() > 10) {
            laserGun.moveLeft();
        }
    }

    /**
     * Siirtää lasertykkiä oikealle, ellei se ole jo
     * aivan peliruudun oikeassa laidassa.
     */
    public void moveGunRight() {
        if (laserGun.getLocationX() < size - 10) {
            laserGun.moveRight();
        }
    }

    /**
     * Metodi lasertykillä ampumiseen. Lisää peliin ammuksen,
     * joka lähtee lasertykin sijaintipaikasta ampumishetkellä.
     */
    public void shoot() {
        if (shots.size() < 1) {
            double locationX = laserGun.getLocationX();
            shots.add(new Shot(locationX, size - 30));
        }
    }

    /**
     * Päivittää pelitilanteen. Siirtää pelihahmoja joiden liiikettä
     * pelaaja ei itse ohjaile (avaruusoliot ja ammukset) ja poistaa
     * kuolleet hahmot pelistä.
     */
    public void update() {
        moveGunShots();
        moveAliens();
        removeDead();

        if (score > hiScore) {
            hiScore = score;
        }

        if (aliens.isEmpty()) {
            won = true;
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
        if (rightAlienX > size || leftAlienX < 0) {
            alienDirection = alienDirection * -1;
            for (Alien alien : aliens) {
                alien.moveDown();
                if (alien.getLocationY() > size - 40) {
                    over = true;
                }
            }
        }

        for (Alien alien : aliens) {
            alien.moveHorizontal(alienDirection, speed);
        }

        leftAlienX += alienDirection * speed;
        rightAlienX += alienDirection * speed;
    }

    private void removeDead() {
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

    /**
     * Tallentaa piste-ennätyksen DAO:n avulla.
     *
     * @throws Exception 
     */
    public void saveHiScore() throws Exception {
        hiScoreDao.saveScore(hiScore);
    }

}

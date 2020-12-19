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
    private int alienDirection;
    private double speed;
    private List<Alien> aliens;
    private Shot gunShot;
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
        this.alienDirection = 1;
        this.speed = speed;

        this.aliens = new ArrayList();
        int width = 30;
        int height = 20;
        int spacing = 20;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 5; j++) {
                double x = 10 + i * (width + spacing);
                double y = 60 + j * (height + spacing);
                this.aliens.add(new Alien(x, y));
            }
        }

        this.gunShot = null;
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

    public Shot getGunShot() {
        return gunShot;
    }

    public void setGunShot(Shot gunShot) {
        this.gunShot = gunShot;
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

        if (gunShot != null) {
            aliveShapes.add(gunShot.getShape());
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
        if (gunShot == null) {
            double locationX = laserGun.getLocationX();
            gunShot = new Shot(locationX, size - 30);
        }
    }

    /**
     * Päivittää pelitilanteen. Siirtää pelihahmoja joiden liiikettä
     * pelaaja ei itse ohjaile (avaruusoliot ja ammukset) ja poistaa
     * kuolleet hahmot pelistä.
     */
    public void update() {
        moveGunShot();
        moveAliens();
        removeDead();

        if (score > hiScore) {
            hiScore = score;
        }

        if (aliens.isEmpty()) {
            won = true;
        }
    }

    private void moveGunShot() {
        if (gunShot == null) {
            return;
        }

        if (gunShot.getLocationY() < 0) {
            gunShot.setAlive(false);
        }

        gunShot.moveUp();
        checkIfAliensHit();
    }

    private void checkIfAliensHit() {
        for (Alien alien : aliens) {
            if (gunShot.hits(alien)) {
                alien.setAlive(false);
                gunShot.setAlive(false);
                score += 10;
            }
        }
    }

    private void moveAliens() {
        checkIfBoundsReached();

        for (Alien alien : aliens) {
            alien.moveHorizontal(alienDirection, speed);
        }
    }

    private void checkIfBoundsReached() {
        boolean boundsReached = false;

        for (Alien alien : aliens) {
            if (alien.getLocationX() < 0 || alien.getLocationX() > size - 40) {
                boundsReached = true;
                break;
            }
        }

        if (boundsReached) {
            alienDirection = alienDirection * -1;
            for (Alien alien : aliens) {
                alien.moveDown();
                if (alien.getLocationY() > size - 40) {
                    over = true;
                }
            }
        }
    }

    private void removeDead() {
        removed = new ArrayList();

        if (gunShot != null && !gunShot.isAlive()) {
            removed.add(gunShot);
            gunShot = null;
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

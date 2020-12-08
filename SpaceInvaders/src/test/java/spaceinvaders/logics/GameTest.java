package spaceinvaders.logics;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import spaceinvaders.dao.HiScoreDao;
import spaceinvaders.domain.Alien;
import spaceinvaders.domain.Shot;

public class GameTest {

    public class StubDao implements HiScoreDao {

        @Override
        public int getHiScore() {
            return 200;
        }

        @Override
        public void saveScore(int score) throws Exception {
        }

    }

    private StubDao stubDao = new StubDao();
    private Game game;
    double delta = 0.0001;

    @Before
    public void setUp() {
        game = new Game(stubDao, 800, 800);
    }

    @Test
    public void constructorSetsCorrectSize() {
        Game newGame = new Game(stubDao, 400, 400);
        assertEquals(400, newGame.getSize(), delta);
        assertEquals(400, newGame.getSize(), delta);
    }

    @Test
    public void startScoreIsZero() {
        assertEquals(0, game.getScore(), delta);
    }

    @Test
    public void updateMovesAllAliens() {
        List<Point2D> oldLocations = new ArrayList();
        for (Alien alien : game.getAliens()) {
            oldLocations.add(new Point2D(alien.getLocationX(), alien.getLocationY()));
        }

        game.update();

        List<Point2D> newLocations = new ArrayList();
        for (Alien alien : game.getAliens()) {
            newLocations.add(new Point2D(alien.getLocationX(), alien.getLocationY()));
        }

        boolean moved = true;
        for (int i = 0; i < game.getAliens().size(); i++) {
            if (oldLocations.get(i).getX() == newLocations.get(i).getX()
                    && oldLocations.get(i).getY() == newLocations.get(i).getY()) {
                moved = false;
            }
        }

        assertTrue(moved);
    }

    @Test
    public void scoreIncrementedIfShotHitsAlien() {
        game.getAliens().add(new Alien(20, 20));
        game.getShots().add(new Shot(20, 21));
        game.update();
        assertEquals(10, game.getScore());
    }

    @Test
    public void alienRemovedWhenHit() {
        Alien alien = new Alien(20, 20);
        game.getAliens().add(alien);
        game.getShots().add(new Shot(20, 21));
        game.update();
        assertFalse(game.getAliens().contains(alien));
    }

}

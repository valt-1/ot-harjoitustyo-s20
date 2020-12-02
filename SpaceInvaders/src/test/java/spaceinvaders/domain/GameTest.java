package spaceinvaders.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import spaceinvaders.dao.HiScoreDao;

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
        assertEquals(400, newGame.getSizeX(), delta);
        assertEquals(400, newGame.getSizeY(), delta);
    }

    @Test
    public void startScoreIsZero() {
        assertEquals(0, game.getScore(), delta);
    }

}

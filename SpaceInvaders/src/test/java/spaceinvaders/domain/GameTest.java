package spaceinvaders.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    double delta = 0.0001;

    @Before
    public void setUp() {
        game = new Game(800, 800);
    }

    @Test
    public void constructorSetsCorrectSize() {
        Game newGame = new Game(400, 400);
        assertEquals(400, newGame.getSizeX(), delta);
        assertEquals(400, newGame.getSizeY(), delta);
    }

    @Test
    public void startScoreIsZero() {
        assertEquals(0, game.getScore(), delta);
    }

}

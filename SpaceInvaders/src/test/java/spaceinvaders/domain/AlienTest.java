package spaceinvaders.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlienTest {

    private Alien alien;
    double startLocationX = 100;
    double startLocationY = 100;
    double delta = 0.0001;

    public AlienTest() {
        alien = new Alien(startLocationX, startLocationY);
    }

    @Test
    public void moveHorizontalMovesLeftCorrectly() {
        alien.moveHorizontal(-1, 1);
        assertTrue(alien.getLocationX() < startLocationX);
        assertEquals(startLocationY, alien.getLocationY(), delta);
    }

    @Test
    public void moveHorizontalMovesRightCorrectly() {
        alien.moveHorizontal(1, 1);
        assertTrue(alien.getLocationX() > startLocationX);
        assertEquals(startLocationY, alien.getLocationY(), delta);
    }

    @Test
    public void moveDownMovesCorrectly() {
        alien.moveDown();
        assertTrue(alien.getLocationY() > startLocationY);
        assertEquals(startLocationX, alien.getLocationX(), delta);
    }
    
}

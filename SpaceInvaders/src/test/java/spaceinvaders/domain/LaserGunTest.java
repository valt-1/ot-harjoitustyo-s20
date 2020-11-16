package spaceinvaders.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spaceinvaders.domain.LaserGun;

public class LaserGunTest {
    
    private LaserGun laserGun;
    double startLocationX = 400;
    double startLocationY = 590;
    double delta = 0.0001;
    
    @Before
    public void setUp() {
        laserGun = new LaserGun(startLocationX, startLocationY);
    }
    
    @Test
    public void moveLeftMovesInCorrectDirection() {
        laserGun.moveLeft();
        assertTrue(laserGun.getLocationX() < startLocationX);
    }
    
    @Test
    public void moveLeftDoesNotChangeCoordinateY() {
        laserGun.moveLeft();
        assertEquals(startLocationY, laserGun.getLocationY(), delta);
    }
    
    @Test
    public void moveRightMovesInCorrectDirection() {
        laserGun.moveRight();
        assertTrue(laserGun.getLocationX() > startLocationX);
    }
    
    @Test
    public void moveRightDoesNotChangeCoordinateY() {
        laserGun.moveRight();
        assertEquals(startLocationY, laserGun.getLocationY(), delta);
    }
}

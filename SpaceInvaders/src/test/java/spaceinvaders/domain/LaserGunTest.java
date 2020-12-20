package spaceinvaders.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaserGunTest {

    LaserGun laserGun;
    double startLocationX = 400;
    double startLocationY = 590;
    double delta = 0.0001;
    double speed = 1;

    @Before
    public void setUp() {
        laserGun = new LaserGun(startLocationX, startLocationY);
    }

    @Test
    public void moveLeftMovesInCorrectDirection() {
        laserGun.moveLeft(speed);
        assertTrue(laserGun.getLocationX() < startLocationX);
    }

    @Test
    public void moveLeftDoesNotChangeCoordinateY() {
        laserGun.moveLeft(speed);
        assertEquals(startLocationY, laserGun.getLocationY(), delta);
    }

    @Test
    public void moveRightMovesInCorrectDirection() {
        laserGun.moveRight(speed);
        assertTrue(laserGun.getLocationX() > startLocationX);
    }

    @Test
    public void moveRightDoesNotChangeCoordinateY() {
        laserGun.moveRight(speed);
        assertEquals(startLocationY, laserGun.getLocationY(), delta);
    }
}

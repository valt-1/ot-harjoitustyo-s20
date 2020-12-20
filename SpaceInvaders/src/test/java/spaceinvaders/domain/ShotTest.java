package spaceinvaders.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShotTest {

    double speed = 3;

    @Test
    public void moveUpMovesInCorrectDirection() {
        Shot shot = new Shot(0, 100);
        shot.moveUp(speed);
        assertTrue(shot.getLocationY() < 100);
    }

    @Test
    public void moveDownMovesInCorrectDirection() {
        Shot shot = new Shot(0, 100);
        shot.moveDown(speed);
        assertTrue(shot.getLocationY() > 100);
    }

}

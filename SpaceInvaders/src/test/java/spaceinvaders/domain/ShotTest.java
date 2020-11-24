package spaceinvaders.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShotTest {

    @Test
    public void moveUpMovesInCorrectDirection() {
        Shot shot = new Shot(0, 100);
        shot.moveUp();
        assertTrue(shot.getLocationY() < 100);
    }

}

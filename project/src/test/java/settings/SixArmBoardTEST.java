package settings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SixArmBoardTEST {
    public static SixArmBoard sixArmBoard;

    @Before
    public void init() {
        sixArmBoard = new SixArmBoard();
    }

    @Test
    public void getXSizeTest() {
        assertEquals(25, sixArmBoard.getXSize());
    }

    @Test
    public void getYSizeTest() {
        assertEquals(17, sixArmBoard.getYSize());
    }
}

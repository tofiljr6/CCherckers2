package GUI;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CoordinatesTEST {

    Coordinates coordinates;
    Coordinates coordinates2;
    Coordinates coordinates3;
    Coordinates coordinates4;


    @Before
    public void init() {
        coordinates = new Coordinates(5, 7);
        coordinates2 = new Coordinates(4, 8);
        coordinates3 = new Coordinates(5, 8);
        coordinates4 = new Coordinates(5, 8);
    }

    @Test
    public void getXTest() {
        assertEquals(5, coordinates.getX());
    }

    @Test
    public void getYTest() {
        assertEquals(7, coordinates.getY());
    }

    @Test
    public void hashCodeTest() {
        assertEquals(31 * 36 + 7,coordinates.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("5 7", coordinates.toString());
    }

    @Test
    public void equalsTest() {
        assertFalse(coordinates.equals(coordinates2));
        assertFalse(coordinates.equals(coordinates3));
        assertTrue(coordinates4.equals(coordinates3));
    }
}

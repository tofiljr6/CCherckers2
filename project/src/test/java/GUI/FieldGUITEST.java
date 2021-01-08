package GUI;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class FieldGUITEST {
    FieldGUI fieldGUI;

    @Before
    public void init() {
        fieldGUI = new FieldGUI();
    }

    @Test
    public void changeColorTest() {
        fieldGUI.changeColor(Color.BLUE);
        assertEquals(Color.BLUE, fieldGUI.getCurrentColor());
    }
}

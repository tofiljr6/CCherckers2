package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldModelTEST {
    FieldModel fieldModel;

    @Before
    public void init() {
        fieldModel = new FieldModel();
    }

    @Test
    public void isTakenTest() {
        assertFalse(fieldModel.isTaken());
        fieldModel.setFieldColor(ColorsFor2Players.GREEN);
        assertTrue(fieldModel.isTaken());
    }

    @Test
    public void setFieldColorTest() {
        assertNull(fieldModel.getColor());
        fieldModel.setFieldColor(ColorsFor2Players.BLUE);
        assertNotNull(fieldModel.getColor());
        assertEquals(ColorsFor2Players.BLUE, fieldModel.getColor());
    }

    @Test
    public void setFieldFreeTest() {
        fieldModel.setFieldColor(ColorsFor4Players.YELLOW);
        assertTrue(fieldModel.isTaken());
        fieldModel.setFieldFree();
        assertFalse(fieldModel.isTaken());
        assertEquals(State.FREE, fieldModel.getState());
    }

    @Test
    public void getColorTest() {
        fieldModel.setFieldColor(ColorsFor2Players.BLUE);
        assertEquals(ColorsFor2Players.BLUE, fieldModel.getColor());
    }

    @Test
    public void setFieldColorHintTest() {
        fieldModel.setFieldColorHint(ColorsFor2Players.GREEN);
        // synchronizedEnums - remember
        assertNull(fieldModel.getColor());
    }

}

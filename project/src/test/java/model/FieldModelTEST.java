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
        fieldModel.setFieldColor(PawnColors.GREEN);
        assertTrue(fieldModel.isTaken());
    }

    @Test
    public void setFieldColorTest() {
        assertNull(fieldModel.getColor());
        fieldModel.setFieldColor(PawnColors.BLUE);
        assertNotNull(fieldModel.getColor());
        assertEquals(PawnColors.BLUE, fieldModel.getColor());
    }

    @Test
    public void setFieldFreeTest() {
        fieldModel.setFieldColor(PawnColors.YELLOW);
        assertTrue(fieldModel.isTaken());
        fieldModel.setFieldFree();
        assertFalse(fieldModel.isTaken());
        assertEquals(State.FREE, fieldModel.getState());
    }

    @Test
    public void getColorTest() {
        fieldModel.setFieldColor(PawnColors.BLUE);
        assertEquals(PawnColors.BLUE, fieldModel.getColor());
    }

    @Test
    public void setFieldColorHintTest() {
        fieldModel.setFieldColorHint(PawnColors.GREEN);
        // synchronizedEnums - remember
        assertNull(fieldModel.getColor());
    }

}

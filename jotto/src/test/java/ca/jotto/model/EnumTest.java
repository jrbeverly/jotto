package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

public class EnumTest {

    @Category(ValidationTests.class)
    @Test
    public void gamestate() throws Exception {
        assertEquals(JGameState.IDLE, JGameState.valueOf("IDLE"));
        assertEquals(JGameState.LOST, JGameState.valueOf("LOST"));
        assertEquals(JGameState.PLAYING, JGameState.valueOf("PLAYING"));
        assertEquals(JGameState.WON, JGameState.valueOf("WON"));
        assertEquals(JGameState.YIELDED, JGameState.valueOf("YIELDED"));
    }

    @Category(ValidationTests.class)
    @Test
    public void validation() throws Exception {
        assertEquals(JValidation.VALID, JValidation.valueOf("VALID"));
        assertEquals(JValidation.NOT_IN_DICTIONARY, JValidation.valueOf("NOT_IN_DICTIONARY"));
        assertEquals(JValidation.INVALID_SIZE, JValidation.valueOf("INVALID_SIZE"));
        assertEquals(JValidation.INVALID_CHARACTER, JValidation.valueOf("INVALID_CHARACTER"));
        assertEquals(JValidation.PREVIOUSLY_GUESSED, JValidation.valueOf("PREVIOUSLY_GUESSED"));
    }
}

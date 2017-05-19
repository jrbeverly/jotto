package ca.jotto.model;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JWordMatchTest {

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void compareTo_null_first() throws Exception {
        JWordMatch.compareTo(null, "MYTHS");
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void compareTo_null_second() throws Exception {
        JWordMatch.compareTo(null, "MYTHS");
    }

    @Category(ValidationTests.class)
    @Test(expected = AssertionError.class)
    public void compareTo_null_mismatch() throws Exception {
        JWordMatch.compareTo("ONE", "THREE");
    }

    @Category(ValidationTests.class)
    @Test
    public void compareTo() throws Exception {
        JWordMatch[] matches = JWordMatch.compareTo("NYMPH", "MYTHS");
        int exact = 0, partial = 0;

        assertNotNull(matches);

        for (JWordMatch match : matches) {
            switch(match) {
                case EXACT: exact++; break;
                case PARTIAL: partial++; break;
            }
        }
        assertEquals(1, exact);
        assertEquals(2, partial);
    }
}

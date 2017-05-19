package ca.jotto.model;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(Parameterized.class)
public class JWordMatchCheckerTest {
    private int exactExpected;
    private int partialExpected;
    private String wordExpected;

    public JWordMatchCheckerTest(String word, int exact, int partial) {
        this.wordExpected = word;
        this.exactExpected = exact;
        this.partialExpected = partial;
    }

    @Parameterized.Parameters
    public static Collection values() {
        return Arrays.asList(new Object[][] {
                {"NYMPH", 1, 2 },
                {"QUAKE", 0, 0 },
                {"PYGMY", 1, 1 },
                {"PSYCH", 0, 3 },
                {"MYTHS", 5, 0 },
        });
    }

    @Test
    public void compareTo() {
        JWordMatch[] matches = JWordMatch.compareTo(wordExpected, "MYTHS");
        int exact = 0, partial = 0;

        assertNotNull(matches);

        for (JWordMatch match : matches) {
            switch(match) {
                case EXACT: exact++; break;
                case PARTIAL: partial++; break;
            }
        }
        assertEquals(exactExpected, exact);
        assertEquals(partialExpected, partial);
    }
}
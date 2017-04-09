package ca.jottoapp.model;

import ca.jotto.JCharset;
import ca.jotto.JGameState;
import ca.jotto.JWordMatch;
import ca.jotto.Jotto;
import ca.jotto.listeners.StateListener;

/**
 * Represents the letters of the board.
 */
public class Letters implements StateListener {

    public JCharset Characters;
    public JWordMatch[] Matches;

    public Letters(JCharset chars) {
        Characters = chars;
        Matches = new JWordMatch[chars.length()];
        for (int i = 0; i < Matches.length; i++) {
            Matches[i] = JWordMatch.NONE;
        }
    }

    @Override
    public void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState) {

    }

    @Override
    public void onCharacterEliminated(Jotto jotto, char character) {
        int index = Characters.get(character);
        Matches[index] = JWordMatch.ELIMINATED;
    }

    @Override
    public void onCharacterExact(Jotto jotto, char character) {
        int index = Characters.get(character);
        Matches[index] = JWordMatch.EXACT;
    }
}

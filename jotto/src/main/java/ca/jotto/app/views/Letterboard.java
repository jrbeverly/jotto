package ca.jotto.app.views;

import ca.jotto.app.model.Letters;
import ca.jotto.model.JGameState;
import ca.jotto.model.JLetterStatus;
import ca.jotto.model.Jotto;
import ca.jotto.model.listeners.StateListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Acts as a visual board displaying which letters have been verified (or partially verified)
 */
public class Letterboard extends JPanel implements StateListener {

    private static final long serialVersionUID = 1L;

    private final Color _clrEliminated = new Color(232, 10, 28);
    private final Color _clrExact = new Color(42, 185, 7);
    private final JLabel[] _lblCharacters;
    private final Letters _letters;
    private int _rows;
    private int _columns;

    public Letterboard(Letters letters) {
        _letters = letters;

        _rows = 2;
        _columns = _letters.Characters.length() / _rows;

        setLayout(new GridLayout(_rows, _columns, 2, 2));
        Font defaultFont = new Font("Aharoni", Font.BOLD, 20);

        _lblCharacters = new JLabel[_letters.Characters.length()];
        for (int i = 0; i < _lblCharacters.length; i++) {
            String label = String.valueOf(_letters.Characters.at(i));
            _lblCharacters[i] = new JLabel(label);

            _lblCharacters[i].setFont(defaultFont);
            _lblCharacters[i].setOpaque(true);
            _lblCharacters[i].setHorizontalAlignment(SwingConstants.CENTER);
            _lblCharacters[i].setVerticalAlignment(SwingConstants.CENTER);
            _lblCharacters[i].setBorder(new LineBorder(new Color(152, 152, 152)));

            add(_lblCharacters[i]);
        }
    }

    public void reset() {
        for (int i = 0; i < _lblCharacters.length; i++) {
            _lblCharacters[i].setBackground(null);
        }
    }

    public void sync() {
        for (int i = 0; i < _lblCharacters.length; i++) {
            JLetterStatus match = _letters.Matches[i];
            switch (match) {
                case NONE:
                    _lblCharacters[i].setBackground(null);
                    break;
                case DISCOVERED:
                    _lblCharacters[i].setBackground(_clrExact);
                    break;
                case ELIMINATED:
                    _lblCharacters[i].setBackground(_clrEliminated);
                    break;
            }

        }
    }

    public void onCharacterEliminated(Jotto jotto, char character) {
        sync();
    }

    public void onCharacterExact(Jotto jotto, char character) {
        sync();
    }

    public void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState) {
    }
}

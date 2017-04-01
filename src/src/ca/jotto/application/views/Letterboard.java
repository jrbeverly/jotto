package ca.jotto.application.views;

import ca.jotto.JCharset;
import ca.jotto.JGameState;
import ca.jotto.Jotto;
import ca.jotto.listeners.StateListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Acts as a visual board displaying which letters have been verified (or partially verified)
 */
public class Letterboard extends JPanel implements StateListener {

    private static final long serialVersionUID = 1L;

    private final Color clrEliminated = new Color(232, 10, 28);
    private final Color clrPartial = Color.ORANGE;
    private final Color clrExact = new Color(42, 185, 7);
    private final JLabel[] lblCharacters;
    private Color clrDefault = Color.BLACK;
    private JCharset _charset;
    private int _rows;
    private int _columns;

    public Letterboard(JCharset charset) {
        _charset = charset;

        _rows = 2;
        _columns = charset.length() / _rows;

        setLayout(new GridLayout(_rows, _columns, 2, 2));
        Font defaultFont = new Font("Aharoni", Font.BOLD, 20);

        lblCharacters = new JLabel[charset.length()];
        for (int i = 0; i < lblCharacters.length; i++) {
            String label = String.valueOf(charset.at(i));
            lblCharacters[i] = new JLabel(label);

            lblCharacters[i].setFont(defaultFont);
            lblCharacters[i].setOpaque(true);
            lblCharacters[i].setHorizontalAlignment(SwingConstants.CENTER);
            lblCharacters[i].setVerticalAlignment(SwingConstants.CENTER);
            lblCharacters[i].setBorder(new LineBorder(new Color(152, 152, 152)));

            add(lblCharacters[i]);
        }
    }

    public void reset() {
        for (int i = 0; i < lblCharacters.length; i++) {
            lblCharacters[i].setBackground(null);
        }
    }

    public void setEliminated(char ch) {
        int index = _charset.get(ch);
        lblCharacters[index].setBackground(clrEliminated);
    }

    public void setExact(char ch) {
        int index = _charset.get(ch);
        lblCharacters[index].setBackground(clrExact);
    }

    public void setRows(int rows) {
        _rows = rows;
        _columns = lblCharacters.length / _rows;
        setLayout(new GridLayout(_rows, _columns));
    }

    public void setPotential(char ch) {
        int index = _charset.get(ch);
        lblCharacters[index].setBackground(clrPartial);
    }

    public void onCharacterEliminated(Jotto jotto, char character) {
        setEliminated(character);
    }

    public void onCharacterExact(Jotto jotto, char character) {
        setExact(character);
    }

    public void onGameStateChanged(Jotto jotto, JGameState oldState, JGameState newState) {
        // TODO Auto-generated method stub
    }
}

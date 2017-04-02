package ca.jottoapp.views;

import ca.jotto.JGuess;
import ca.jotto.Jotto;
import ca.jotto.listeners.TurnListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Acts as a visual board for displaying the history of guesses in the jotto
 * game.
 */
public class Guessboard extends JPanel implements TurnListener {

    private JTable _table;

    /**
     * Create the panel.
     */
    public Guessboard() {
        setLayout(new GridLayout(0, 1, 0, 0));

        _table = new JTable();

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                new String[]{"", "", "", "", "", "", ""});
        _table.setModel(model);

        DefaultTableCellRenderer characterRenderer = new DefaultTableCellRenderer();
        characterRenderer.setHorizontalAlignment(JLabel.CENTER);
        characterRenderer.setFont(new Font("Tahoma", Font.BOLD, 18));
        characterRenderer.setForeground(Color.BLACK);
        _table.setDefaultRenderer(Object.class, characterRenderer);

        add(_table);

    }

    public void reset() {
        DefaultTableModel model = (DefaultTableModel) _table.getModel();
        model.setRowCount(0);
    }

    @Override
    public void onTurnGuess(Jotto jotto, JGuess guess) {
        DefaultTableModel model = (DefaultTableModel) _table.getModel();
        Object[] objs = new Object[7];

        for (int i = 0; i < guess.size(); i++) {
            objs[i] = new Character(guess.getChar(i));
        }

        objs[guess.size()] = new Integer(guess.getExact());
        objs[guess.size() + 1] = new Integer(guess.getPartial());

        model.addRow(objs);
    }

    @Override
    public void onTurnIncorrect(Jotto jotto, JGuess guess) {
    }

    @Override
    public void onTurnCorrect(Jotto jotto, JGuess guess) {
    }
}

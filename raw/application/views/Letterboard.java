package jotto.application.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import jotto.core.JGameState;
import jotto.core.Jotto;
import jotto.core.listeners.StateListener;

/**
 * Acts as a visual board displaying which letters have been verified (or partially verified)
 * */
public class Letterboard extends JPanel implements StateListener {

	private static final long serialVersionUID = 1L;
	private static final int ALPHABET = 26;
	private static final char START = 'A';
	private static final char END = 'Z';

	private int rowCount = 2;
	private int columnCount = 26 / 2;

	private Color _isEliminated = new Color(232, 10, 28); // red
	private Color _isPartial = Color.ORANGE;
	private Color _isExact = new Color(42, 185, 7); // green
	private Color _default = Color.BLACK;

	private JLabel[] _labels;

	public Letterboard() {
		setLayout(new GridLayout(rowCount, columnCount, 2, 2));

		_labels = new JLabel[ALPHABET];
		char ch = START;
		for (int i = 0; i < ALPHABET; i++) {
			_labels[i] = new JLabel(ch + "");

			_labels[i].setFont(new Font("Aharoni", Font.BOLD, 20));
			_labels[i].setOpaque(true);
			_labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			_labels[i].setVerticalAlignment(SwingConstants.CENTER);
			_labels[i].setBorder(new LineBorder(new Color(152, 152, 152)));

			add(_labels[i]);
			ch++;
		}

		_default = _labels[0].getBackground();
	}

	public void reset() {
		for (int i = 0; i < ALPHABET; i++) {
			_labels[i].setBackground(_default);
		}
	}

	public void setRows(int rows) {
		rowCount = rows;
		columnCount = 26 / rows;
		setLayout(new GridLayout(rowCount, columnCount));
	}

	public void setEliminated(char ch) {
		int index = ch - START;
		if (index < 0 && index >= ALPHABET) {
			return;
		}

		_labels[index].setBackground(_isEliminated);
	}

	public void setExact(char ch) {
		int index = ch - START;
		if (index < 0 && index >= ALPHABET) {
			return;
		}

		_labels[index].setBackground(_isExact);
	}

	public void setPotential(char ch) {
		int index = ch - START;
		if (index < 0 && index >= ALPHABET) {
			return;
		}

		_labels[index].setBackground(_isPartial);
	}

	public void onCharacterEliminated(Jotto jotto, char character) {
		setEliminated(character);
	}

	public void onCharacterExact(Jotto jotto, char character) {
		setExact(character);

	}

	@Override
	public void onGameStateChanged(Jotto jotto, JGameState oldState,
			JGameState newState) {
		// TODO Auto-generated method stub

	}
}

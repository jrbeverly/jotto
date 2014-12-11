package jotto.views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import jotto.core.JGameState;
import jotto.core.JGuess;
import jotto.core.JHistory;
import jotto.core.Jotto;
import Jotto.core.Listeners.JottoListener;

public class Guessboard extends JPanel implements JottoListener {

	private JTable _table;

	/**
	 * Create the panel.
	 */
	public Guessboard() {
		setLayout(new GridLayout(0, 1, 0, 0));

		_table = new JTable();

		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "", "",
				"", "", "", "", "" });
		_table.setModel(model);

		DefaultTableCellRenderer characterRenderer = new DefaultTableCellRenderer();
		characterRenderer.setHorizontalAlignment(JLabel.CENTER);
		characterRenderer.setFont(new Font("Tahoma", Font.BOLD, 18));
		characterRenderer.setForeground(Color.BLACK);
		_table.setDefaultRenderer(Object.class, characterRenderer);

		add(_table);

	}
	
	public void reset()
	{
		DefaultTableModel model = (DefaultTableModel) _table.getModel();
		model.setRowCount(0);
	}

	@Override
	public void onPlayerGuess(JGuess guess) {
		DefaultTableModel model = (DefaultTableModel) _table.getModel();
		Object[] objs = new Object[7];

		for (int i = 0; i < guess.getWordSize(); i++) {
			objs[i] = new Character(guess.getChar(i));
		}
		
		objs[guess.getWordSize()] = new Integer(guess.getExact());
		objs[guess.getWordSize() + 1] = new Integer(guess.getPartial());
		
		model.addRow(objs);
	}

	@Override
	public Jotto getGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onGameStateChanged(JGameState oldState, JGameState newState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMatchStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMatchOver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerYield() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerWin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerLoss() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerWrong(JGuess guess) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCharacterEliminated(char character) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCharacterExact(char character) {
		// TODO Auto-generated method stub

	}
}

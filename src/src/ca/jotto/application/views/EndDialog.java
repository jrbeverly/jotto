package ca.jotto.application.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

/**
 * Acts as the visual dialog box fort the message commands (specifically end
 * game)
 * */
public class EndDialog extends JDialog implements ActionListener {

	private final JPanel pnlInfo = new JPanel();

	public EndDialog() {
		setBounds(100, 100, 450, 233);
		getContentPane().setLayout(new BorderLayout());
		pnlInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlInfo, BorderLayout.CENTER);
		pnlInfo.setLayout(new BorderLayout(0, 0));

		JLabel lblImage = new JLabel("");
		pnlInfo.add(lblImage, BorderLayout.NORTH);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMessage = new JLabel("Would you like to play again?");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setVerticalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlInfo.add(lblMessage, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Play Again?");
		JButton cancelButton = new JButton("Exit");

		okButton.addActionListener(this);

		buttonPane.add(okButton);
		buttonPane.add(cancelButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}

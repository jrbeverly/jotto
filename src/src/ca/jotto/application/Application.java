package ca.jotto.application;

import ca.jotto.*;
import ca.jotto.application.views.EndDialog;
import ca.jotto.application.views.Guessboard;
import ca.jotto.application.views.Letterboard;
import ca.jotto.listeners.GameListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The core application that makes use of the Guessboard/Letterboard views to
 * implement a Jotto GUI.
 */
public class Application extends JFrame implements GameListener {

    private static final int JOTTO_DEFAULT_SIZE = 5;
    private static final String JOTTO_WORD_FILE = "resources/words.txt";

    private Jotto jotto;
    private JMatch match;
    private JDictionary dictionary;
    private JDifficulty difficulty;

    private JPanel contentPane;
    private JPanel pnlMain;
    private JFormattedTextField txtGuess;
    private JLabel lblMessage;
    private final ButtonGroup btgDifficulty = new ButtonGroup();
    private Guessboard gboard;
    private Letterboard lboard;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Application frame = new Application();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void reset() {
        gboard.reset();
        lboard.reset();
        match = null;
    }

    public Application() throws IOException {
        try {
            dictionary = JDictionary.fromFile(JCharset.DEFAULT, JOTTO_WORD_FILE);
        } catch (IOException ioexc) {
            System.err.println("");
        }

        jotto = new Jotto(dictionary);
        difficulty = JDifficulty.Normal;

        setTitle("Jotto Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 426, 508);
        setMinimumSize(new Dimension(430, 500));

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel pnlTitle = new JPanel();
        pnlTitle.setBackground(SystemColor.textHighlight);
        contentPane.add(pnlTitle, BorderLayout.NORTH);
        pnlTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Jotto");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBackground(SystemColor.textHighlight);
        pnlTitle.add(lblTitle, BorderLayout.NORTH);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 18));

        pnlMain = new JPanel();
        pnlMain.setEnabled(false);
        contentPane.add(pnlMain, BorderLayout.CENTER);
        pnlMain.setLayout(new BorderLayout(0, 0));

        JPanel pnlGuesses = new JPanel();
        pnlMain.add(pnlGuesses);
        pnlGuesses.setLayout(new BorderLayout(0, 10));

        JPanel pnlInput = new JPanel();
        pnlGuesses.add(pnlInput, BorderLayout.NORTH);
        pnlInput.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblGuess = new JLabel("Guesses");
        lblGuess.setHorizontalAlignment(SwingConstants.CENTER);
        pnlInput.add(lblGuess);
        lblGuess.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblGuess.setForeground(Color.BLACK);

        txtGuess = new JFormattedTextField();
        pnlInput.add(txtGuess);

        lblMessage = new JLabel("");
        lblMessage.setForeground(UIManager.getColor("ToolBar.dockingForeground"));
        pnlInput.add(lblMessage);

        // /ADD MENU BAR
        JMenuBar mnbItems = setupMenu();
        pnlTitle.add(mnbItems, BorderLayout.WEST);

        // //ADD HISTORY PANEL
        JPanel pnlHistory = new JPanel();
        JPanel pnlLabels = new JPanel();
        JLabel lblWords = new JLabel("WORDS");
        JLabel lblVars = new JLabel("EXACT/PARTIAL");

        pnlHistory.setLayout(new BorderLayout(0, 0));
        pnlLabels.setLayout(new GridLayout(1, 0, 0, 0));

        lblWords.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblVars.setFont(new Font("Tahoma", Font.BOLD, 16));

        lblWords.setForeground(UIManager.getColor("Tree.selectionBackground"));
        lblVars.setForeground(UIManager.getColor("Tree.selectionBackground"));

        lblVars.setHorizontalAlignment(SwingConstants.RIGHT);

        pnlGuesses.add(pnlHistory, BorderLayout.CENTER);
        pnlHistory.add(pnlLabels, BorderLayout.NORTH);
        pnlLabels.add(lblWords);
        pnlLabels.add(lblVars);

        // //
        gboard = new Guessboard();
        pnlHistory.add(gboard, BorderLayout.CENTER);

        lboard = new Letterboard(JCharset.DEFAULT);
        contentPane.add(lboard, BorderLayout.SOUTH);

        txtGuess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JValidation test = match.validate(txtGuess.getText());
                if (test == JValidation.VALID) {
                    String txt = txtGuess.getText();

                    lblMessage.setText("");
                    txtGuess.setText("");
                    txtGuess.setForeground(Color.BLACK);

                    try {
                        match.guess(txt);
                    } catch (Exception exc) {

                    }

                } else {
                    switch (test) {
                        case NOT_IN_DICTIONARY:
                            lblMessage.setText("Guess is not in the dictionary.");
                            break;
                        case INVALID_SIZE:
                            lblMessage.setText("Guess is not a valid size.");
                            break;
                        case INVALID_CHARACTER:
                            lblMessage
                                    .setText("Guess does not contain valid characters.");
                            break;
                        case PREVIOUSLY_GUESSED:
                            lblMessage.setText("You have already guessed ["
                                    + txtGuess.getText() + "].");
                            break;
                        default:
                            lblMessage.setText("Guess is invalid.");
                            break;
                    }
                    txtGuess.setForeground(Color.RED);
                }
            }
        });

        jotto.getEventMap().addListener(lboard);
        jotto.getEventMap().addListener(gboard);
        jotto.getEventMap().addListener(this);

        setComponents(pnlMain, false);
    }

    private JMenuItem btnStart;
    private JMenuItem btnReset;
    private JMenuItem btnYield;
    private JMenu mnuDifficulty;

    private JMenuBar setupMenu() {
        JMenuBar mnbItems = new JMenuBar();

        JMenu mnuFile = new JMenu("File");
        btnStart = new JMenuItem("Start");
        btnReset = new JMenuItem("Reset");
        btnYield = new JMenuItem("Yield");
        JSeparator mnuSeparator = new JSeparator();
        JMenuItem btnExit = new JMenuItem("Exit");

        mnuDifficulty = new JMenu("Difficulty");
        JRadioButtonMenuItem btnEasy = new JRadioButtonMenuItem("Easy");
        JRadioButtonMenuItem btnNormal = new JRadioButtonMenuItem("Normal");
        JRadioButtonMenuItem btnHard = new JRadioButtonMenuItem("Hard");

        btnNormal.setSelected(true);
        btnReset.setEnabled(false);
        btnYield.setEnabled(false);

        mnbItems.add(mnuFile);
        mnuDifficulty.add(btnEasy);
        mnuDifficulty.add(btnNormal);
        mnuDifficulty.add(btnHard);

        btgDifficulty.add(btnEasy);
        btgDifficulty.add(btnNormal);
        btgDifficulty.add(btnHard);

        mnbItems.add(mnuDifficulty);
        mnuFile.add(btnStart);
        mnuFile.add(btnReset);
        mnuFile.add(mnuSeparator);
        mnuFile.add(btnYield);
        mnuFile.add(mnuSeparator);
        mnuFile.add(btnExit);

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnReset.setEnabled(true);
                btnYield.setEnabled(true);
                setComponents(pnlMain, true);

                btnStart.setEnabled(false);
                mnuDifficulty.setEnabled(false);

                JWord word = dictionary.getRandomWord(difficulty.getLevel());
                match = jotto.start(word);
                match.start();
                System.out.println(word.getWord());
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnStart.setEnabled(true);
                mnuDifficulty.setEnabled(true);

                btnReset.setEnabled(false);
                btnYield.setEnabled(false);
                setComponents(pnlMain, false);

                reset();
            }
        });

        btnYield.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    match.yield();
                } catch (Exception exc) {
                }

                btnStart.setEnabled(true);
                mnuDifficulty.setEnabled(true);

                btnReset.setEnabled(false);
                btnYield.setEnabled(false);
                setComponents(pnlMain, false);

                reset();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        btnEasy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty = JDifficulty.Easy;
            }
        });

        btnNormal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty = JDifficulty.Normal;
            }
        });

        btnHard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty = JDifficulty.Hard;
            }
        });

        return mnbItems;
    }

    private void setComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                setComponents((Container) component, enable);
            }
        }
    }

    private void showDialog() {
        EndDialog dialog = new EndDialog();
        switch (match.getState()) {
            case LOST:
                dialog.setTitle("You have lost!");
                break;
            case YIELDED:
                dialog.setTitle("You have yielded!");
                break;
            case WON:
                dialog.setTitle("You have won!");
                break;
            default:
                System.exit(0);
                return;
        }

        btnStart.setEnabled(true);
        mnuDifficulty.setEnabled(true);

        btnReset.setEnabled(false);
        btnYield.setEnabled(false);
        setComponents(pnlMain, false);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        reset();
    }

    @Override
    public void onMatchStart(Jotto jotto, JMatch match) {
    }

    @Override
    public void onMatchOver(Jotto jotto, JMatch match) {
    }

    @Override
    public void onPlayerYield(Jotto jotto, JMatch match) {
        showDialog();
    }

    @Override
    public void onPlayerWin(Jotto jotto, JMatch match) {
        showDialog();
    }

    @Override
    public void onPlayerLoss(Jotto jotto, JMatch match) {
        showDialog();
    }
}

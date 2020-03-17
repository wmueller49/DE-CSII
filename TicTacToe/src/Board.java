/**
 * @author William Mueller
 * DE CSII
 * TicTacToeHashCode
 * 3/16/2020
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

abstract class Board extends JFrame implements ActionListener {

	private JButton buttons[][];
	private JLabel lblHashCode;
	private JLabel lblWinTitle;

	private String boardString = "";

	/**
	 * creates a board with a title and sets up the frame
	 * @param title the title of the board
	 */
	public Board(String title) {
		super(title);
		setupFrame();
	}

	/**
	 * sets the label on the screen to the inputted hashcode
	 * @param hashcode the number to be the label
	 */
	public void setHashCodeLabel(int hashcode) {
		lblHashCode.setText("" + hashcode);
	}

	/**
	 * sets the label on the screen to say if the shown board is a winner or not
	 * @param result to be the label
	 */
	public void setWinnerLabel(String result) {
		lblWinTitle.setText(result);
	}

	/**
	 * sets the label to be a winner or loser
	 * @param result is true if the board is a winner, false if not
	 */
	public void setWinnerLabel(boolean result) {
		if (result)
			setWinnerLabel("Winner");
		else
			setWinnerLabel("Loser");
	}

	// required because of abstract method, but not used
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	JPanel setupPanelOne() {
		JPanel panel = new JPanel();
		JLabel lblHCTitle = new JLabel("Hash Code");
		;
		lblHashCode = new JLabel("" + myHashCode());
		lblWinTitle = new JLabel(""); // Will say either Winner or Loser
		setWinnerLabel(TicTacToe.isWin(boardString));
		panel.setLayout(new FlowLayout());
		panel.add(lblHCTitle);
		panel.add(lblHashCode);
		panel.add(lblWinTitle);
		return panel;
	}

	/**
	 * Creates a JPanel to show a certain board string
	 * @return the panel that is created
	 */
	private JPanel setupPanelTwo() {
		JButton b;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToe.ROWS, TicTacToe.COLS));
		buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];

		int count = 1;

		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				b = new JButton("" + ch);
				boardString += ch;
				b.setActionCommand("" + r + ", " + c);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn = (JButton) e.getSource();
						btn.setText("" + cycleValue(btn.getText().charAt(0)));
						resetBoardString();
						setHashCodeLabel(myHashCode());
						setWinnerLabel(isWin());

					}
				});
				panel.add(b);
				buttons[r][c] = b;
			}

		return panel;
	}

	/**
	 * allows the user to switch between the three characters in the UI
	 * @param ch the given character
	 * @return the next char to switch to
	 */
	private static char cycleValue(char ch) {
		switch (ch) {
		case 'x':
			return 'o';
		case 'o':
			return ' ';
		default:
			return 'x';
		}
	}

	/**
	 * sets up the frame's size and visibility
	 */
	private void setupFrame() {
		JPanel panel2 = new JPanel();

		// Setup Frame
		super.setSize(250, 200);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		// Setup Panels
		panel2 = setupPanelTwo(); // panelOne displays a value that requires panelTwo to be ready
		super.add(setupPanelOne());
		super.add(panel2);

		super.setVisible(true);
	}

	/**
	 * returns a random charcter
	 * @return a random character, 'x', 'o', or ' '
	 */
	private char randomXO() {
		int rnd = (int) (Math.random() * TicTacToe.CHAR_POSSIBILITIES);
		switch (rnd) {
		case 1:
			return 'x';
		case 2:
			return 'o';
		default:
			return ' ';
		}
	}

	abstract int myHashCode();

	abstract boolean isWin(String s);

	abstract boolean isWin();

	/**
	 * returns a character at a row and column
	 * @param row the row of the character
	 * @param col the column of a character
	 * @return the character at the given row, col
	 */
	public char charAt(int row, int col) {
		String value = buttons[row][col].getText();
		if (value.length() > 0)
			return value.charAt(0);
		else
			return '*';
	}
   
	/**
	 * returns a character at a row and column
	 * @param s the string to search through
	 * @param row the row of the character
	 * @param col the column of the character
	 * @return the character at the given row, col in s
	 */
   public char charAt(String s, int row, int col) {
     int pos = row * TicTacToe.COLS + col;
     if (s.length() >= pos)
       return s.charAt(pos);
     else
       return '*';   
   }

   /**
    * shows a certain character
    * @param s the string to be shown
    */
	public void show(String s) {
		int pos = 0;
		String letter;
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = s.charAt(pos);
				switch (ch) {
				case '1':
					letter = "x";
					break;
				case '2':
					letter = "o";
					break;
				case '0':
					letter = " ";
					break;
				default:
					letter = "" + ch;
				}
				buttons[r][c].setText(letter);
				pos++;
			}
	}

	/**
	 * resets the board string
	 */
	public void resetBoardString() {
   boardString = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				boardString += buttons[r][c].getText();
			}
	}

	/**
	 * sets the board string to a given string
	 * @param s the string to replace the current board string
	 */
	public void setBoardString(String s) {
		boardString = s;
		show(s);
	}

	/**
	 * gets the current board string
	 * @return the current board string
	 */
	public String getBoardString() {
		return boardString;
	}

	/**
	 * makes and displays a random string on the board
	 */
	public void displayRandomString() {
		boardString = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				boardString += ch;
				buttons[r][c].setText("" + ch);
			}
		setHashCodeLabel(myHashCode());
		setWinnerLabel(isWin());
	}

}
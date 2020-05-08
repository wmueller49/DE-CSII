/**
 * @author Owner
 *
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SudokuSquare extends JPanel{
	
	private final static int FONTSIZE = 40;
	private Color backgroundColor = Color.white;
	private boolean flag;
	
	private boolean defaultNum;
	private boolean isSolved;
	private int value;

	/**
	 * 
	 */
	public SudokuSquare() {
		defaultNum = false;
		isSolved = false;
	}
	
	public SudokuSquare(boolean b) {
		defaultNum = b;
		isSolved = false;
	}
	
	public SudokuSquare(boolean b, int v) {
		defaultNum = b;
		value = v;
		isSolved = false;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isDefaultNum() {
		return defaultNum;
	}
	
	public void setSolved(boolean b) {
		isSolved = b;
	}
	
	public void setValue(int v) {
		if(defaultNum) {
			
		}
		else {
			value = v;
		}
	}
	
	public String toString() {
		return value + "";
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("Helvetica", Font.BOLD, FONTSIZE));
		this.setBackground(backgroundColor);
		
		if(getValue() == -1) {
			int x = (this.getWidth() / 2) - FONTSIZE/4; // - letter.length()/2;
            int y = (this.getHeight() / 2) + FONTSIZE/4;
            String value = " ";
            
            g.drawString(value, x, y);
		}
		else {
			if(isSolved) {
				g.setColor(Color.decode("#329932"));
			}
			else if(defaultNum) {
				g.setColor(Color.decode("#ff4c4c"));
			}
			else {
				g.setColor(Color.black);
			}
			
			int x = (this.getWidth() / 2) - FONTSIZE/4; // - letter.length()/2;
            int y = (this.getHeight() / 2) + FONTSIZE/4;
            String value = getValue() + "";
            
            g.drawString(value, x, y);
		}
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		this.setBorder(blackline);
	}
	
}

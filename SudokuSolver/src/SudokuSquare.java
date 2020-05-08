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
	private int value;

	/**
	 * 
	 */
	public SudokuSquare() {
		defaultNum = false;
	}
	
	public SudokuSquare(boolean b) {
		defaultNum = b;
	}
	
	public SudokuSquare(boolean b, int v) {
		defaultNum = b;
		value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isDefaultNum() {
		return defaultNum;
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
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, FONTSIZE));
		g.setColor(Color.black);
		this.setBackground(backgroundColor);
		
		if(getValue() == -1) {
			int x = (this.getWidth() / 2) - FONTSIZE/4; // - letter.length()/2;
            int y = (this.getHeight() / 2) + FONTSIZE/4;
            String value = " ";
            
            g.drawString(value, x, y);
		}
		else {
			int x = (this.getWidth() / 2) - FONTSIZE/4; // - letter.length()/2;
            int y = (this.getHeight() / 2) + FONTSIZE/4;
            String value = getValue() + "";
            
            g.drawString(value, x, y);
		}
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		this.setBorder(blackline);
	}
	
}

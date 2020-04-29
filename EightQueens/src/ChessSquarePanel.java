/**
 * @author William Mueller
 * DE CSII
 * Eight Queens
 * 
 * ChessSquarePanel
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ChessSquarePanel extends JPanel{
	
	/**
	 * Field that says how large the queens will appear on the panel
	 */
	private final static int FONTSIZE = 40;  
	
	/**
	 * the background color of the ChessSquarePanel
	 */
	private Color backgroundColor;
	
	/**
	 * the boolean value of the ChessSquarePanel, true if it displays a queen, false otherwise
	 */
	private boolean flag;
	
	/**
	 * Constructor that creates a ChessSquarePanel with backgroundColor c and flag value f
	 * @param c the color of the ChessSquarePanel
	 * @param f the flag value of the ChessSquarePanel
	 */
	public ChessSquarePanel(Color c, boolean f) {
		backgroundColor = c;
		flag = f;
	}
	
	/**
	 * sets the flag of the ChessSquarePanel to value b
	 * @param b the new value of flag
	 */
	public void setFlag(boolean b) {
		flag = b;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, FONTSIZE));
		g.setColor(Color.gray);
		this.setBackground(backgroundColor);
		
		if(flag) {
			int x = (this.getWidth() / 2) - FONTSIZE/4; // - letter.length()/2;
            int y = (this.getHeight() / 2) + FONTSIZE/4;
            
            g.drawString("\u2655", x, y);
		}
		
	}

}

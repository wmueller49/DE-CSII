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

public class SudokuSquare extends JPanel {

   private final static int FONTSIZE = 40;
   Font FONT = new Font("Helvetica", Font.BOLD, FONTSIZE);
   
   private final static Color SOLVED_COLOR = Color.decode("#329932");
   private final static Color DEFAULT_COLOR = Color.decode("#ff4c4c");
   private final static Color GUESSED_COLOR = Color.BLACK;
   private final static Color BACKGROUND_COLOR = Color.WHITE;
   
   private boolean flag;
	
   private boolean defaultNum;
   private boolean isSolved;
   private int value;

	/**
	 * 
	 */
   public SudokuSquare() {
      this(false);
   }
	
   public SudokuSquare(boolean b) {
      this(b, 0);	
   }
  
   public SudokuSquare(boolean b, int v) {
      defaultNum = b;
      value = v;
      isSolved = false;
   // SETUP Panel
      this.setBackground(BACKGROUND_COLOR);
      Border blackline = BorderFactory.createLineBorder(Color.black);
      this.setBorder(blackline);
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
   	
      g.setFont(FONT);
   	
      if(getValue() == -1) {
         int x = (this.getWidth() / 2) - FONTSIZE/4; // - letter.length()/2;
         int y = (this.getHeight() / 2) + FONTSIZE/4;
         String value = " ";
            
         g.drawString(value, x, y);
      }
      else {
         if(isSolved) {
            g.setColor(SOLVED_COLOR);
         }
         else if(defaultNum) {
            g.setColor(DEFAULT_COLOR);
         }
         else {
            g.setColor(GUESSED_COLOR);
         }
      	
         int x = (this.getWidth() / 2) - FONTSIZE/4; // - letter.length()/2;
         int y = (this.getHeight() / 2) + FONTSIZE/4;
            
         g.drawString(""+value, x, y);
      }
   }	
}

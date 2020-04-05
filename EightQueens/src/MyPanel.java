import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class MyPanel extends JPanel {

private final static int FONTSIZE = 20;

// These fields are used whenever PaintComponent is executed to update the panel
private String letter;
private Color backColor;

MyPanel() {
  backColor = Color.BLUE;
  letter = "Q";
}

MyPanel(Color c, String ch) {
  backColor = c;
  letter = ch;
}

 public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setFont(new Font("TimesRoman", Font.PLAIN, FONTSIZE));
    this.setBackground(backColor);
    g.setColor(Color.RED);
    
   // removeAll();   // Someone mentioned panels not updating properly, this
                     // may help, but does not seem necessary 
                     // x and y center the String, adjust as necessary
            int x = (this.getWidth() / 2) - FONTSIZE/4; // - letter.length()/2;
            int y = (this.getHeight() / 2) + FONTSIZE/4;
            g.drawString(letter, x, y);
  }
  
   public void setBackColor(Color c) {
     backColor = c;
     repaint();   // forces paintComponent to execute
   }

   public void setLetter(String letter) {
     this.letter = letter;
     repaint();  // forces paintComponent to execute
     }
}
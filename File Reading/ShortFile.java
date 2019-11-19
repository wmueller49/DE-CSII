import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class ShortFile extends JPanel {
   public final static int PANEL_WIDTH = 400;
   public final static int PANEL_HEIGHT = 600;

   List<Shape> shapes;

   ShapeGraphics() {
      JFrame window = createFrame();
      createPanel();
      window.add(this);  // Because the class extends JPanel, this is a JPanel ... so, what is being added to the frame is the jpanel
      window.setVisible(true);
      shapes = new ArrayList<Shape>();    // This is an empty list of shapes that will be drawn on the JPanel
   }

   public void addShape(Shape s) {
      shapes.add(s);
   }
   
   private JFrame createFrame() {
      JFrame frame = new JFrame("Practicing Graphics");
      frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //    frame.setVisible(true);
      frame.setResizable(true);
   //   frame.setIconImage(new Image(""));
      return frame; 
   }
   
   private void createPanel() {
      setBackground(Color.BLUE);
   }
    
   @Override
   public void paintComponent(Graphics g) { // This method is what is redrawn whenever Java decides it is required.  
                                             // For us, we use the super classes paintComponent to ensure that is 
                                             // all taken care of, then we print each shape in the list.  There is a 
                                             // temporary debug print statement in the loop right now.
      super.paintComponent(g);
      for (Shape s : shapes) {
         if (s != null) {
            System.out.println(s);
            s.drawMe(g);               // THIS IS A REALLY COOL CALL ... IT ACTUALLY CALLS THE DRAW ME OF THE SPECIFIC SHAPE IN THE ARRAY
         }
      }
   }             
}


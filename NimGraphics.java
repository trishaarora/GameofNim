import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.Color;

 public class NimGraphics extends JComponent
  {
   
   /**
    * The paintComponent method creates the graphics for GameofNim.java
    * This method requires one object of type Graphics
    * @param g The Graphics object to store the graphics state that are used for drawing operations
    * */
    public void paintComponent(Graphics g)
    {
      Graphics2D g2 = (Graphics2D) g; //Creates a two-dimensional graphics object
      
      g2.drawString("Number of Marbles: " + (int) GameofNim.getMarblesRemaining(), 20, 50);
      
      g2.setColor(new Color(218,165,32)); //Sets color to gold
      
      int length = (int) ((GameofNim.getMarblesRemaining()/GameofNim.getTotalMarbles()) * 760.00);
      
      Rectangle counter = new Rectangle(20, 100, length, 100); //Creates rectangle
      
      g2.draw(counter); //Draws outline
      g2.fill(counter); //Fills rectangle 
    }
    
  }
import javax.swing.*;
import java.util.Random;

//tarora and efreedman: This program simulates the Game of Nim

public class GameofNim
{
  //Fields
  private static int numMarbles; //Keeps track of the number of marbles in the pile
  private static int totalMarbles; //The total number of marbles in the initial pile   
  private static boolean isStupid; //Value of isStupid determines whether the computer plays smart or stupid
  private static boolean humTurn; //Value of humTurn determines whether the turn is taken by the user or the computer
  
  public static void main(String[] args)
  {
    initializeGame();
    
    //creates JFrame
    JFrame frame = new JFrame();
    NimGraphics nim = new NimGraphics();
    
    initializeGraphics(frame, nim);
    
    JOptionPane.showMessageDialog(null, "Welcome to the Game of Nim!");
    JOptionPane.showMessageDialog(null, "The initial size of the pile is " + numMarbles + " marbles.");
    
    updateGraphics(frame,nim);
    
    while(numMarbles > 1)
    {
      if(humTurn)
      {
        JOptionPane.showMessageDialog(null, "Your turn!");
        humanMove();
        updateGraphics(frame,nim);
      }
      else
      {
        JOptionPane.showMessageDialog(null, "It's the Computer's turn!");
        computerMove();
        updateGraphics(frame,nim);
      }
      
      humTurn = !humTurn; //Switches turn between user and computer
    }
  } 
  
  /**
   * The initializeGame method determines the number of marbles in the initial pile,
   * whether the computer plays smart or stupid and whether the user or the computer takes the first turn
   * */
  public static void initializeGame()
  {
    Random r = new Random(); //Creates a random number generator
    totalMarbles = r.nextInt(90) + 10; //Sets the range of random numbers from 10 to 100
    numMarbles = totalMarbles;
    
    isStupid = r.nextBoolean(); //Determines whether the computer plays smart or stupid
    humTurn = r.nextBoolean(); //Determines whether the user or the computer takes the first turn
  }
  
  /**
   * The computerMove method simulates the computer's turn
   * @return numMarbles The number of marbles left in the pile after the computer takes it's turn
   * */
  public static int computerMove()
  {
    int compMarbles = 1; //Number of marbles taken by the computer
    Random m = new Random();
    
    //If the computer is playing stupid
    if(isStupid)
    {
      compMarbles = m.nextInt(numMarbles/2) + 1; //The computer takes a random legal number of marbles
      numMarbles = numMarbles - compMarbles;
    }
    
    //If the computer is playing smart
    else
    {
      boolean madeMove = false;
      int i = numMarbles;
      
      //If the number of marbles in the pile is currently one less than a power of two
      if (numMarbles == 7 || numMarbles == 15 || numMarbles == 31 
            || numMarbles == 63)
      {
        compMarbles = m.nextInt(numMarbles/2) + 1; //The computer takes a random legal number of marbles
        numMarbles = numMarbles - compMarbles;
        madeMove = true;
      } 
      
      //If the size of the pile is three marbles or less
      else if (numMarbles <= 3)
      {
        compMarbles = 1; //The computer takes one marble (the only possible legal move)
        numMarbles -= compMarbles;
      }
      
      //The computer takes off enough marbles to make the size of the pile a power of two minus 1
      while (i >= numMarbles/2 && madeMove == false)
      {
        if (i == 63 || i == 31 || i == 15 || i == 7 || i == 3)
        {
          compMarbles = numMarbles - i;
          numMarbles = numMarbles - compMarbles;
          madeMove = true;
        }
        else
          i--;  
      }
    }
    
    JOptionPane.showMessageDialog(null, "The Computer took " + compMarbles + " marbles from the pile.");
    JOptionPane.showMessageDialog(null, "There are " + numMarbles + " marbles left in the pile.");
    
    //If there is only one marble left in the pile after the computer has taken it's turn
    if(numMarbles == 1)
    {
      JOptionPane.showMessageDialog(null, "You lose :(");
    }
    
    return numMarbles;
  }
  
  /**
   * The humanMove method simulates the user's turn
   * @return numMarbles The number of marbles left in the pile after the computer takes it's turn
   */
  public static int humanMove()
  {
    int userMarbles; //Number of marbles taken by the user
    userMarbles = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of marbles "
                                                                    + "you wish to take from the pile " 
                                                                    + "(enter a number between 1 and " + numMarbles/2 + ") :"));
    
    //Input validation
    while (userMarbles > numMarbles/2 || userMarbles < 1)
    {
      JOptionPane.showMessageDialog(null, "That is an invalid number of marbles.");
      userMarbles = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of marbles "
                                                                    + "you wish to take from the pile " 
                                                                    + "(enter a number between 1 and " + numMarbles/2 + ") :"));
    }
    
    numMarbles = numMarbles - userMarbles;
    JOptionPane.showMessageDialog(null, "There are " + numMarbles + " marbles left in the pile.");
    
    //If there is only one marble left in the pile after the user has taken his or her turn
    if(numMarbles == 1)
    {
      JOptionPane.showMessageDialog(null, "Congratulations! You win :)");
    }
    
    return numMarbles;
  }
  
  /**
   * The initializeGraphics method initializes the visual representation of the program
   * */
  public static void initializeGraphics(JFrame f, NimGraphics n)
  {
    //Defines JFrame constants
    final int WIDTH = 800;
    final int HEIGHT = 250;
    
    //Creates JFrame 
    //JFrame frame = new JFrame();
    f.setSize(WIDTH,HEIGHT);
    f.setTitle("The Game of Nim!");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Adds Graphics to frameNimGraphics 
    f.add(n);
    f.setVisible(true);
    
  }
  
  /**
   * The updateGraphics method allows the JFrame to be updated after each turn is taken
   * */
  public static void updateGraphics(JFrame f, NimGraphics n)
  {
    n.repaint(); //Updates graphics to represent the new size of the pile of marbles
    f.setVisible(true);
  }
  
  /**
   * The getMarblesRemaining method is a getter method
   * @return numMarbles The number of marbles in the pile
   * */
  public static double getMarblesRemaining()
  {
    return (double) numMarbles;
  }
  
  /**
   * The getTotalMarbles method is a getter method
   * @return getTotalMarbles The number of marbles in the initial pile
   * */
  public static double getTotalMarbles()
  {
    return (double) totalMarbles;
  }
}
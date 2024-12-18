package SimpleSwingProgram; // Replace with your desired package name

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MINA extends JFrame { // Use lowercase class name

  public static void main(String[] args) { // Use String[] args for main method
    MINA frame = new MINA("MyTitle");
    frame.setSize(new Dimension(728, 400)); // Use setSize instead of setBounds
    frame.setVisible(true);
  }

  public MINA(String title) {
    setTitle(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();

    // Load and display the image
    URL imageURL = getClass().getClassLoader().getResource("resources/A.png");
    if (imageURL != null) {
      ImageIcon image = new ImageIcon(imageURL);
      JLabel imageLabel = new JLabel(image);
      panel.add(imageLabel);
    } else {
      System.out.println("Error: Image not found!");
    }

    getContentPane().add(panel); // Add panel to content pane
  }
}
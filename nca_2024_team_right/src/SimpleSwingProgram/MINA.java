package SimpleSwingProgram; // Replace with your desired package name

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MINA extends JFrame { // Use lowercase class name

    public MINA(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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


// Load and display the image
        URL imageURL1 = getClass().getClassLoader().getResource("resources/B.png");
        if (imageURL1 != null) {
            ImageIcon image = new ImageIcon(imageURL1);
            JLabel imageLabel = new JLabel(image);
            panel.add(imageLabel);
        } else {
            System.out.println("Error: Image not found!");
        }

//Load and display the image
        URL imageURL2 = getClass().getClassLoader().getResource("resources/C.png");
        if (imageURL2 != null) {
            ImageIcon image = new ImageIcon(imageURL2);
            JLabel imageLabel = new JLabel(image);
            panel.add(imageLabel);
        } else {
            System.out.println("Error: Image not found!");
        }

//Load and display the image
        URL imageURL3 = getClass().getClassLoader().getResource("resources/D.png");
        if (imageURL3 != null) {
            ImageIcon image = new ImageIcon(imageURL3);
            JLabel imageLabel = new JLabel(image);
            panel.add(imageLabel);
        } else {
            System.out.println("Error: Image not found!");
        }

//Load and display the image
        URL imageURL4 = getClass().getClassLoader().getResource("resources/b.png");
        if (imageURL4 != null) {
            ImageIcon image = new ImageIcon(imageURL4);
            JLabel imageLabel = new JLabel(image);
            panel.add(imageLabel);
        } else {
            System.out.println("Error: Image not found!");
        }

//Load and display the image
        URL imageURL5 = getClass().getClassLoader().getResource("resources/b.png");
        if (imageURL5 != null) {
            ImageIcon image = new ImageIcon(imageURL5);
            JLabel imageLabel = new JLabel(image);
            panel.add(imageLabel);
        } else {
            System.out.println("Error: Image not found!");
        }

//Load and display the image
        URL imageURL6 = getClass().getClassLoader().getResource("resources/b.png");
        if (imageURL6 != null) {
            ImageIcon image = new ImageIcon(imageURL6);
            JLabel imageLabel = new JLabel(image);
            panel.add(imageLabel);
        } else {
            System.out.println("Error: Image not found!");
        }


//Load and display the image
        URL imageURL7 = getClass().getClassLoader().getResource("resources/b.png");
        if (imageURL7 != null) {
            ImageIcon image = new ImageIcon(imageURL7);
            JLabel imageLabel = new JLabel(image);
            panel.add(imageLabel);
        } else {
            System.out.println("Error: Image not found!");
        }

//Load and display the image
        URL imageURL8 = getClass().getClassLoader().getResource("resources/b.png");
        if (imageURL8 != null) {
            ImageIcon image = new ImageIcon(imageURL8);
            JLabel imageLabel = new JLabel(image);
            panel.add(imageLabel);
        } else {
            System.out.println("Error: Image not found!");
        }

//Load and display the image
        URL imageURL9 = getClass().getClassLoader().getResource("resources/b.png");
        if (imageURL9 != null) {
            ImageIcon image = new ImageIcon(imageURL9);
            JLabel imageLabel = new JLabel(image);
            panel.add(imageLabel);
        } else {
            System.out.println("Error: Image not found!");
        }
    }

    public static void main(String[] args) { // Use String[] args for main method
        MINA frame = new MINA("MyTitle");
        frame.setSize(new Dimension(728, 400)); // Use setSize instead of setBounds
        frame.setVisible(true);
    }
}



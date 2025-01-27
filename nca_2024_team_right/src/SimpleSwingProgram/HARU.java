package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;

public class HARU {
    public static void main(String[] args) {
        // Get the default screen device (the screen the program will run on)
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        // Create a frame
        JFrame frame = new JFrame("HARU Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(true);  // Remove the title bar and window decorations

        // Set the frame to be fullscreen
        gd.setFullScreenWindow(frame);
        
        // Create a panel with a MouseListener
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println("Panel clicked at: " + e.getPoint());
            }
        });

        // Create a button (without adding an action listener, so no action will happen)
        JButton button = new JButton("I do nothing");
        button.setBounds(50, 50, 200, 50);

        // Add the button to the panel
        panel.add(button);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame visible (already fullscreen)
        frame.setVisible(true);
    }
}

package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HARU {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Full Screen Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Click Me!");

        // Add the button to the frame
        frame.add(button, BorderLayout.CENTER);

        // Add a ComponentListener to dynamically resize the button
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current size of the frame
                int width = frame.getWidth();
                int height = frame.getHeight();

                // Set the button's size to match the frame
                button.setBounds(0, 0, width, height);
            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
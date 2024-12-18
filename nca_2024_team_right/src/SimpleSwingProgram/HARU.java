package SimpleSwingProgram;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class HARU {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Full Screen Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button1 = new JButton(" ");
        JButton button2 = new JButton("×");
        // Add the button to the frame
        frame.add(button1, BorderLayout.CENTER);
        frame.add(button2, BorderLayout.CENTER);
        button1.add(button2);

        // Add a ComponentListener to dynamically resize the button
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the current size of the frame
                int width = frame.getWidth();
                int height = frame.getHeight();

                // Set the button's size to match the frame
                button1.setBounds(0, 0, width, height);
            }
        });
       button2.setSize(20,20);

        button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "登録されました");
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "クリア");
            }
        });


        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
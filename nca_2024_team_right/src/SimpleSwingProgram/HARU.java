package SimpleSwingProgram;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HARU {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Frame");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when the close button is clicked
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Set panel layout to null for manual control over positioning
        panel.setBounds(0, 0, 400, 400); // Ensure panel takes up the whole frame
        frame.add(panel); // Add panel to frame

        JButton button1 = new JButton(" ");
        button1.setBounds(100, 100, 50, 50); // Position and size for button1
        button1.setBackground(Color.GREEN); // Just to distinguish it visually

        JButton button2 = new JButton("×");
        button2.setBounds(160, 100, 50, 50); // Position and size for button2

        // Action for button1
        button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "登録されました");
            }
        });

        // Action for button2
        button2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "クリア");
            }
        });

        // Add buttons to the panel
        panel.add(button1);
button1.add(button2);

        frame.setVisible(true);
    }
}



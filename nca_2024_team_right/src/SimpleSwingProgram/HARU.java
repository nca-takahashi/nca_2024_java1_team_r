package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HARU {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Centered Button in Button");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            JButton outerButton = new JButton("Outer");
            outerButton.setPreferredSize(new Dimension(200, 100));

            JPanel buttonPanel = new JPanel(new BorderLayout());
            buttonPanel.setPreferredSize(outerButton.getPreferredSize());
            buttonPanel.setBorder(outerButton.getBorder());
            buttonPanel.setOpaque(false);

            JButton innerButton = new JButton("X");
            innerButton.setPreferredSize(new Dimension(30, 30)); // Set smaller preferred size for inner button
            innerButton.setFont(new Font("Arial", Font.BOLD, 16)); // Optionally, adjust font size

            JPanel innerButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            innerButtonPanel.add(innerButton);
            innerButtonPanel.setOpaque(false);

            buttonPanel.add(innerButtonPanel, BorderLayout.CENTER);

            buttonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (!innerButton.getBounds().contains(evt.getPoint())) {
                        JOptionPane.showMessageDialog(frame, "Outer area Clicked!");
                    }
                }
            });

            innerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Inner Button Clicked!");
                }
            });

            frame.add(buttonPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
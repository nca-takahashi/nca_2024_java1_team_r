package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HARU {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Haru's Buttons");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            // Create the fake button panel
            JPanel fakeButtonPanel = new JPanel(new BorderLayout());
            fakeButtonPanel.setPreferredSize(new Dimension(200, 100));
            fakeButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel fakeButtonLabel = new JLabel("Click Me!(advertisement)");
            fakeButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
            fakeButtonPanel.add(fakeButtonLabel, BorderLayout.CENTER);

            fakeButtonPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        JOptionPane.showMessageDialog(frame, "Thank you for clicking the fake button!");
                    }
                }
            });

            JPanel realButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Use FlowLayout.RIGHT
            realButtonPanel.setOpaque(false); // Make the panel transparent
            JButton haruButton = new JButton("X");
            haruButton.setPreferredSize(new Dimension(20, 20));
            haruButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Thank you for clicking Haru's real button!");
                    Window window = SwingUtilities.getWindowAncestor(haruButton);
                    if (window != null) {
                        window.dispose();
                    }
                }
            });
            realButtonPanel.add(haruButton);

            // Add the real button panel to the fake button panel's NORTH position
            fakeButtonPanel.add(realButtonPanel, BorderLayout.NORTH);

            // Add the fake button panel to the frame
            frame.add(fakeButtonPanel);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
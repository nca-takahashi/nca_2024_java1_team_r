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
            frame.setLayout(new BorderLayout()); // Set BorderLayout for full expansion

            // Create the fake button panel
            JPanel fakeButtonPanel = new JPanel(new BorderLayout());
            fakeButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel fakeButtonLabel = new JLabel("Click Me!");
            fakeButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
            fakeButtonPanel.add(fakeButtonLabel, BorderLayout.CENTER);

            fakeButtonPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        JOptionPane.showMessageDialog(frame, "登録されました");
                    }
                }
            });

            JPanel realButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            realButtonPanel.setOpaque(false);
            JButton haruButton = new JButton("X");
            haruButton.setPreferredSize(new Dimension(1, 1)); // Make the close button visible
            haruButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "クリア！");
                    Window window = SwingUtilities.getWindowAncestor(haruButton);
                    if (window != null) {
                        window.dispose();
                    }
                }
            });
            realButtonPanel.add(haruButton);
            
            fakeButtonPanel.add(realButtonPanel, BorderLayout.NORTH);
            frame.add(fakeButtonPanel, BorderLayout.CENTER); // Make it expand fully

            frame.setSize(400, 300); // Default size
            frame.setVisible(true);
        });
    }
}

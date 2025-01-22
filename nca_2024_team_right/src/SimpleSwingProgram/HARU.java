package SimpleSwingProgram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class HARU {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Haru's Buttons");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout()); // Use BorderLayout for the frame layout

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

            JPanel realButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Use FlowLayout.RIGHT
            realButtonPanel.setOpaque(false); // Make the panel transparent
            JButton haruButton = new JButton("X");
            haruButton.setPreferredSize(new Dimension(1,1 )); // Adjust the button size
            haruButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	JOptionPane.showMessageDialog(frame, "クリア！！！");
                    Window window = SwingUtilities.getWindowAncestor(haruButton);
                    if (window != null) {
                        window.dispose();
                    }
                }
            });

            fakeButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            realButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            realButtonPanel.add(haruButton);

            // Add the real button panel to the fake button panel's NORTH position
            fakeButtonPanel.add(realButtonPanel, BorderLayout.NORTH);

            // Add the fake button panel to the frame
            frame.add(fakeButtonPanel, BorderLayout.CENTER);

            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Start in full-screen mode
            frame.setVisible(true);
        });
    }
}


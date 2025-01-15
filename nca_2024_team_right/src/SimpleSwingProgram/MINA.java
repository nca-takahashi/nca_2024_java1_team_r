package SimpleSwingProgram;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class MINA {

    private static final int MAX_WIDTH = 400; // Smaller maximum width for images
    private static final int MAX_HEIGHT = 300; // Smaller maximum height for images
    private final List<String> imagePaths = new ArrayList<>(); // Stores image paths
    private final Random random = new Random();
    private int openWindows = 0; // Counter for open windows

    public MINA() {
        // Load image paths from resources folder
        loadImagesFromResources();

        // Randomly shuffle image paths
        Collections.shuffle(imagePaths);

        // Display each image in a separate window
        displayImages();
    }

    public static void main(String[] args) {
        new MINA();
    }

    private void loadImagesFromResources() {
        URL folderURL = getClass().getClassLoader().getResource("resources");
        if (folderURL != null) {
            File folder = new File(folderURL.getPath());
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.isFile() && isImageFile(file)) {
                    imagePaths.add("resources" + "/" + file.getName());
                }
            }
        } else {
            System.out.println("Error: Resources folder not found!");
        }
    }

    private boolean isImageFile(File file) {
        String extension = getExtension(file);
        return extension != null && (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("gif"));
    }

    private String getExtension(File file) {
        String fileName = file.getName();
        int dotPos = fileName.lastIndexOf('.');
        if (dotPos >= 0) {
            return fileName.substring(dotPos + 1);
        }
        return null;
    }

    private void displayImages() {
        for (String imagePath : imagePaths) {
            displayImageInWindow(imagePath);
        }
    }

    private void displayImageInWindow(String imagePath) {
        URL imageURL = getClass().getClassLoader().getResource(imagePath);
        if (imageURL != null) {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            Image image = imageIcon.getImage();

            // Resize image if it exceeds the smaller maximum dimensions
            if (image.getWidth(null) > MAX_WIDTH || image.getHeight(null) > MAX_HEIGHT) {
                image = image.getScaledInstance(MAX_WIDTH, MAX_HEIGHT, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(image);
            }

            JFrame imageFrame = new JFrame();
            imageFrame.setUndecorated(true);
            imageFrame.setLayout(new BorderLayout());

            JLabel imageLabel = new JLabel(imageIcon);
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));

            imageLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

            // Create close button
            JButton closeButton = new JButton("X");
            closeButton.setBounds(imageIcon.getIconWidth() - 4, 0, 4, 4);
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    imageFrame.dispose();
                }
            });
            layeredPane.add(closeButton, JLayeredPane.PALETTE_LAYER);

            // Add mouse listener to open more windows if clicked outside the close button
            layeredPane.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!closeButton.getBounds().contains(e.getPoint())) {
                        for (int i = 0; i < 3; i++) { // Open 3 new windows on misclick
                            displayImageInWindow(imagePath);
                        }
                        playAnnoyingSound();
                    }
                }
            });

            imageFrame.add(layeredPane, BorderLayout.CENTER);
            imageFrame.pack();

            // Set random location
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = random.nextInt(screenSize.width - imageFrame.getWidth());
            int y = random.nextInt(screenSize.height - imageFrame.getHeight());
            imageFrame.setLocation(x, y);

            // Randomly change the position of the close button
            closeButton.setBounds(random.nextInt(imageIcon.getIconWidth() - 4), random.nextInt(imageIcon.getIconHeight() - 4), 4, 4);

            imageFrame.setVisible(true);

            // Increment the counter when a window is opened
            openWindows++;

            // Add window listener to reopen the window if closed without clicking the close button
            imageFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    displayImageInWindow(imagePath);
                    imageFrame.dispose();
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    // Decrement the counter when a window is closed
                    openWindows--;
                    if (openWindows == 0) {
                        // Show dialog when all windows are closed
                        JOptionPane.showMessageDialog(null, "All windows are closed!");
                    }
                }
            });
        } else {
            System.out.println("Error: Image not found: " + imagePath);
        }
    }

    private void playAnnoyingSound() {
        // Play a sound (this is a placeholder, you need to add actual sound playing code)
        Toolkit.getDefaultToolkit().beep();
    }
}
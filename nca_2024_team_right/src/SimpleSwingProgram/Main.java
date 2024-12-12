package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class Main {
    public static void main(String[] args) {


        //Main Manu
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Simple Swing App");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(new Dimension(300, 335));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel label = new JLabel("Hello, Java Swing!");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            JButton button1 = new JButton("Click me!");
            button1.setAlignmentX(Component.CENTER_ALIGNMENT);
            button1.addActionListener(e -> label.setText("Button clicked!"));

            JButton button2 = new JButton("Digital Clock");
            button2.setAlignmentX(Component.CENTER_ALIGNMENT);
            button2.addActionListener(e -> {
                JFrame secondWindow = new JFrame("Digital Clock");
                secondWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                secondWindow.setSize(new Dimension(300, 200));

                JPanel clockPanel = new JPanel();
                JLabel timeLabel = new JLabel();
                timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
                timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel dateLabel = new JLabel();
                // dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                Timer timer = new Timer(1000, event -> {
                    Calendar now = Calendar.getInstance();
                    String timeString = String.format("%02d:%02d:%02d",
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            now.get(Calendar.SECOND));
                    timeLabel.setText(timeString);

                    SimpleDateFormat dateFormatter = new SimpleDateFormat("E MMM dd, yyyy");
                    String dateString = dateFormatter.format(now.getTime());
                    dateLabel.setText(dateString);
                });
                timer.start();

                clockPanel.add(dateLabel);
                clockPanel.add(timeLabel);

                secondWindow.setContentPane(clockPanel);
                secondWindow.setLocationRelativeTo(null);
                secondWindow.setVisible(true);
            });

            JButton button3 = new JButton("Roll Dice");
            button3.setAlignmentX(Component.CENTER_ALIGNMENT);
            button3.addActionListener(e -> {
                int randomNum = (int) (Math.random() * 6) + 1;
                label.setText("You rolled a " + randomNum);
            });

            JButton button4 = new JButton("Change Color");
            button4.setAlignmentX(Component.CENTER_ALIGNMENT);
            button4.addActionListener(e -> {
                Random random = new Random();
                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                panel.setBackground(new Color(r, g, b));
            });

            JButton button5 = new JButton("Infinity Window");
            button5.setAlignmentX(Component.CENTER_ALIGNMENT);
            button5.addActionListener(e -> {
                JFrame infinityFrame = new JFrame("Infinity Window");
                infinityFrame.setSize(400, 300);
                infinityFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                infinityFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        SwingUtilities.invokeLater(() -> {
                            JFrame newFrame = new JFrame("Infinity Window");
                            newFrame.setSize(400, 300);
                            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            newFrame.addWindowListener(this);

                            JLabel label = new JLabel("Catch me if you can!");
                            label.setHorizontalAlignment(JLabel.CENTER);
                            newFrame.getContentPane().add(label);

                            newFrame.setVisible(true);
                        });
                    }
                });


                JLabel thelabel = new JLabel("You can't escape!");
                thelabel.setHorizontalAlignment(JLabel.CENTER);
                infinityFrame.getContentPane().add(thelabel);

                infinityFrame.setVisible(true);
            });


            JButton button6 = new JButton("Yuga's maze game");
            button6.setAlignmentX(Component.CENTER_ALIGNMENT);
            button6.addActionListener(e -> {
                YUGA yuga = new YUGA();
                YUGA.main(null);
            });
            JButton button7 = new JButton("Mina's Ad Closing game");
            button7.setAlignmentX(Component.CENTER_ALIGNMENT);
            button7.addActionListener(e -> {
                MINA mina = new MINA(null);
                MINA.main();
            });


            //Add buttons to panel
            panel.add(button1);
            panel.add(button2);
            panel.add(button3);
            panel.add(button4);
            panel.add(button5);
            panel.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            //YUGA
            panel.add(button6);
            //MINA
            panel.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            panel.add(button7);


            window.add(panel);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }
}

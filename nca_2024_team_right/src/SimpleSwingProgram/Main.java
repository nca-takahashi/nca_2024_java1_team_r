package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class Main {
    public static void main(String[] args) {

        //Main Manu
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Simple Swing App");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(new Dimension(440, 320));
            window.setResizable(false);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS)); // Horizontal layout

            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

            JLabel label = new JLabel("Hello, Java Swing!");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel1.add(label);

            JButton button = new JButton("Click me!");
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> label.setText("Button clicked!"));

            JButton clockButton = new JButton("Digital Clock");
            clockButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            clockButton.addActionListener(event -> {
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

                Timer timer = new Timer(1000, e -> {
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

            JButton diceButton = new JButton("Roll Dice");
            diceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            diceButton.addActionListener(event -> {
                int randomNum = (int) (Math.random() * 6) + 1;
                label.setText("You rolled a " + randomNum);
            });

            JButton colorButton = new JButton("Change Color (Check border)");
            colorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            colorButton.addActionListener(event -> {
                Random random = new Random();
                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                window.setBackground(new Color(r, g, b));
                r = random.nextInt(256);
                g = random.nextInt(256);
                b = random.nextInt(256);
                mainPanel.setBackground(new Color(r, g, b));
                r = random.nextInt(256);
                g = random.nextInt(256);
                b = random.nextInt(256);
                panel1.setBackground(new Color(r, g, b));
                r = random.nextInt(256);
                g = random.nextInt(256);
                b = random.nextInt(256);
                panel2.setBackground(new Color(r, g, b));

            });


            JButton infinityButton = new JButton("Infinity Window");
            infinityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            infinityButton.addActionListener(event -> {
                JFrame infinityFrame = new JFrame("Infinity Window");
                infinityFrame.setSize(400, 300);
                infinityFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                infinityFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent windowEvent) {
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


                JLabel messageLabel = new JLabel("You can't escape!");
                messageLabel.setHorizontalAlignment(JLabel.CENTER);
                infinityFrame.getContentPane().add(messageLabel);

                infinityFrame.setVisible(true);
            });

            JButton calculatorButton = new JButton("Simple Calculator");
            calculatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            calculatorButton.addActionListener(event -> {
                // Create a new window
                JFrame calcWindow = new JFrame("Simple Calculator");
                calcWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                calcWindow.setSize(new Dimension(300, 200));
                calcWindow.setVisible(true);

                JPanel calcPanel = new JPanel();
                calcPanel.setLayout(new GridLayout(5, 1)); // Use GridLayout with 5 rows, 1 column
                calcWindow.add(calcPanel); // Add Panel to Window(JFrame)

                // Set a Title
                JLabel titleLabel = new JLabel("Simple Calculator!");
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER); //Center the title
                calcPanel.add(titleLabel);

                JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //Panel for input fields
                JTextField firstField = new JTextField(10);
                inputPanel.add(firstField);
                JTextField operationField = new JTextField(1);
                inputPanel.add(operationField);
                JTextField secondField = new JTextField(10);
                inputPanel.add(secondField);
                calcPanel.add(inputPanel); // Add input panel to main panel

                JLabel resultLabel = new JLabel("RESULT");
                resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
                calcPanel.add(resultLabel);
                JButton calcButton = new JButton("Calculate");
                calcButton.addActionListener(e -> {
                    // Get values from text fields
                    double firstNumber = Double.parseDouble(firstField.getText());
                    String operation = operationField.getText();
                    double secondNumber = Double.parseDouble(secondField.getText());

                    // Perform calculation based on operation
                    double result = 0;
                    switch (operation) {
                        case "+":
                            result = firstNumber + secondNumber;
                            break;
                        case "-":
                            result = firstNumber - secondNumber;
                            break;
                        case "*":
                            result = firstNumber * secondNumber;
                            break;
                        case "/":
                            if (secondNumber == 0) {
                                resultLabel.setText("Error: Division by zero!"); // Error if user tried to divide by 0
                            } else {
                                result = firstNumber / secondNumber;
                            }
                            break;
                        default:
                            resultLabel.setText("Error: Invalid operation!");
                            return; // Add this to prevent further execution

                    }

                    // Update result label
                    resultLabel.setText("RESULT"); // Clear any previous message
                    resultLabel.setText(resultLabel.getText() + ": " + result);
                });
                calcPanel.add(calcButton);
            });


            JButton mazeButton = new JButton("Yuga's maze game");
            mazeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            mazeButton.addActionListener(event -> YUGA.main());


            JButton adButton = new JButton("Mina's Ad Closing game");
            adButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            adButton.addActionListener(event -> MINA.main(args));


            JButton clickButton = new JButton("Taisei's Clicking Game");
            clickButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            clickButton.addActionListener(event -> TAISEI.main(args));


            JButton haruButton = new JButton("Haru's Subscribing Button");
            haruButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            haruButton.addActionListener(e -> HARU.main(args));

            JButton HIDEButton = new JButton("Hideaki's Sugoroku Game");
            HIDEButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            HIDEButton.addActionListener(e -> HIDEAKI.main(args));


            //Add buttons to panel
            //Shogo
            panel1.add(button);
            panel1.add(clockButton);
            panel1.add(diceButton);
            panel1.add(colorButton);
            panel1.add(infinityButton);
            panel1.add(calculatorButton);
            // YUGA
            panel2.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            panel2.add(mazeButton);
            // MINA
            panel2.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            panel2.add(adButton);
            // TAISEI
            panel2.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            panel2.add(clickButton);
            // HARU
            panel2.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            panel2.add(haruButton);
            // HIDEAKI
            panel2.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            panel2.add(HIDEButton);


            // Add Panels to mainPanel
            mainPanel.add(panel1);
            mainPanel.add(panel2);
            window.add(mainPanel);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }
}

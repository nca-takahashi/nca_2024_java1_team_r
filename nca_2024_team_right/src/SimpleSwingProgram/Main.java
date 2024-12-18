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
            window.setSize(new Dimension(300, 375));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel label = new JLabel("Hello, Java Swing!");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);

            JButton button = new JButton("Click me!");
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(_ -> label.setText("Button clicked!"));

            JButton clockButton = new JButton("Digital Clock");
            clockButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            clockButton.addActionListener(_ -> {
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

                Timer timer = new Timer(1000, _ -> {
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
            diceButton.addActionListener(_ -> {
                int randomNum = (int) (Math.random() * 6) + 1;
                label.setText("You rolled a " + randomNum);
            });

            JButton colorButton = new JButton("Change Color");
            colorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            colorButton.addActionListener(_ -> {
                Random random = new Random();
                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                panel.setBackground(new Color(r, g, b));
            });

            JButton infinityButton = new JButton("Infinity Window");
            infinityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            infinityButton.addActionListener(e -> {
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


                JLabel messageLabel = new JLabel("You can't escape!");
                messageLabel.setHorizontalAlignment(JLabel.CENTER);
                infinityFrame.getContentPane().add(messageLabel);

                infinityFrame.setVisible(true);
            });

            JButton calculatorButton = new JButton("Simple Calculator");
            calculatorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            calculatorButton.addActionListener(e -> {
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
                calcButton.addActionListener(event -> {
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
            mazeButton.addActionListener(e -> YUGA.main());


            JButton adButton = new JButton("Mina's Ad Closing game");
            adButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            adButton.addActionListener(e -> MINA.main());


            JButton clickButton = new JButton("Taisei's Clicking Game");
            clickButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            clickButton.addActionListener(e -> TAISEI.main());


            //Add buttons to panel
            //Shogo
            panel.add(button);
            panel.add(clockButton);
            panel.add(diceButton);
            panel.add(colorButton);
            panel.add(infinityButton);
            panel.add(calculatorButton);
            // YUGA
            panel.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            panel.add(mazeButton);
            // MINA
            panel.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            panel.add(adButton);
            // TAISEI
            panel.add(Box.createRigidArea(new Dimension(10, 20))); // 10px wide, 20px high
            panel.add(clickButton);


            window.add(panel);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }
}

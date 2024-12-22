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
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Simple Swing App");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(new Dimension(440, 320));
            window.setResizable(false);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

            JLabel label = new JLabel("Hello, Java Swing!");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel1.add(label);

            JButton button = createButton("Click me!", e -> label.setText("Button clicked!"));
            JButton clockButton = createButton("Digital Clock", e -> showDigitalClock());
            JButton diceButton = createButton("Roll Dice", e -> label.setText("You rolled a " + (new Random().nextInt(6) + 1)));
            JButton colorButton = createButton("Change Color (Check border)", e -> changeColors(window, mainPanel, panel1, panel2));
            JButton infinityButton = createButton("Infinity Window", e -> showInfinityWindow());
            JButton calculatorButton = createButton("Simple Calculator", e -> showCalculator());
            JButton mazeButton = createButton("Yuga's maze game", e -> YUGA.main());
            JButton adButton = createButton("Mina's Ad Closing game", e -> MINA.main(args));
            JButton clickButton = createButton("Taisei's Clicking Game", e -> TAISEI.main(args));
            JButton haruButton = createButton("Haru's Subscribing Button", e -> HARU.main(args));
            JButton HIDEButton = createButton("Hideaki's Sugoroku Game", e -> HIDEAKI.main(args));
            JButton IBUKIButton = createButton("Ibuki's Quiz Game", e -> IBUKI.main(args));

            panel1.add(button);
            panel1.add(clockButton);
            panel1.add(diceButton);
            panel1.add(colorButton);
            panel1.add(infinityButton);
            panel1.add(calculatorButton);

            panel2.add(Box.createRigidArea(new Dimension(10, 20)));
            panel2.add(mazeButton);
            panel2.add(Box.createRigidArea(new Dimension(10, 20)));
            panel2.add(adButton);
            panel2.add(Box.createRigidArea(new Dimension(10, 20)));
            panel2.add(clickButton);
            panel2.add(Box.createRigidArea(new Dimension(10, 20)));
            panel2.add(haruButton);
            panel2.add(Box.createRigidArea(new Dimension(10, 20)));
            panel2.add(HIDEButton);
            panel2.add(Box.createRigidArea(new Dimension(10, 20)));
            panel2.add(IBUKIButton);

            mainPanel.add(panel1);
            mainPanel.add(panel2);
            window.add(mainPanel);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }

    private static JButton createButton(String text, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(actionListener);
        return button;
    }

    private static void showDigitalClock() {
        JFrame secondWindow = new JFrame("Digital Clock");
        secondWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        secondWindow.setSize(new Dimension(300, 200));

        JPanel clockPanel = new JPanel();
        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel dateLabel = new JLabel();
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
    }

    private static void changeColors(JFrame window, JPanel mainPanel, JPanel panel1, JPanel panel2) {
        Random random = new Random();
        window.setBackground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        mainPanel.setBackground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        panel1.setBackground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        panel2.setBackground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
    }

    private static void showInfinityWindow() {
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
    }

    private static void showCalculator() {
        JFrame calcWindow = new JFrame("Simple Calculator");
        calcWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calcWindow.setSize(new Dimension(300, 200));
        calcWindow.setVisible(true);

        JPanel calcPanel = new JPanel();
        calcPanel.setLayout(new GridLayout(5, 1));
        calcWindow.add(calcPanel);

        JLabel titleLabel = new JLabel("Simple Calculator!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        calcPanel.add(titleLabel);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField firstField = new JTextField(10);
        inputPanel.add(firstField);
        JTextField operationField = new JTextField(1);
        inputPanel.add(operationField);
        JTextField secondField = new JTextField(10);
        inputPanel.add(secondField);
        calcPanel.add(inputPanel);

        JLabel resultLabel = new JLabel("RESULT");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        calcPanel.add(resultLabel);

        JButton calcButton = new JButton("Calculate");
        calcButton.addActionListener(e -> {
            double firstNumber = Double.parseDouble(firstField.getText());
            String operation = operationField.getText();
            double secondNumber = Double.parseDouble(secondField.getText());

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
                        resultLabel.setText("Error: Division by zero!");
                        return;
                    } else {
                        result = firstNumber / secondNumber;
                    }
                    break;
                default:
                    resultLabel.setText("Error: Invalid operation!");
                    return;
            }

            resultLabel.setText("RESULT: " + result);
        });
        calcPanel.add(calcButton);
    }
}
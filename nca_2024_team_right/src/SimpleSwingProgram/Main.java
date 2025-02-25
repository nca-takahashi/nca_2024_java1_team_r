package SimpleSwingProgram;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("シンプルなSwingプログラム");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(new Dimension(655, 510));
            window.setResizable(false);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
            panel1.setBackground(Color.WHITE);
            panel2.setBackground(Color.WHITE);

            JLabel label = new JLabel("Hello, Java Swing!");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("SansSerif", Font.BOLD, 16));
            panel1.add(label);
            JLabel label2 = new JLabel("私達のプロジェクトへようこそ！");
            label2.setAlignmentX(Component.CENTER_ALIGNMENT);
            label2.setFont(new Font("SansSerif", Font.PLAIN, 14));
            panel1.add(label2);
            JLabel label3 = new JLabel("Made By 小坂祥悟");
            label3.setAlignmentX(Component.CENTER_ALIGNMENT);
            label3.setFont(new Font("SansSerif", Font.PLAIN, 14));
            panel1.add(label3);
            JLabel label4 = new JLabel("Made By 愉快な仲間たち");
            label4.setAlignmentX(Component.CENTER_ALIGNMENT);
            label4.setFont(new Font("SansSerif", Font.PLAIN, 14));
            


            JButton button = createModernButton("私をクリックしてください！", e -> label.setText("クリックされた！!"));
            JButton clockButton = createModernButton("電子時計", e -> showDigitalClock());
            JButton diceButton = createModernButton("サイコロを振る", e -> label.setText("あなたは:" + (new Random().nextInt(6) + 1)));
            JButton colorButton = createModernButton("色変え（ボーダーを確認する）", e -> changeColors(window, mainPanel, panel1, panel2));
            JButton infinityButton = createModernButton("無限窓", e -> showInfinityWindow());
            JButton calculatorButton = createModernButton("シンプル計算機", e -> showCalculator());
            JButton mazeButton = createModernButton("林勇牙 － 迷路ゲーム", e -> YUGA.main(args));
            JButton adButton = createModernButton("元澤魅愛 － 詐欺広告増殖削除ゲーム", e -> MINA.main(args));
            JButton clickButton = createModernButton("杉山太成 － 的あてゲーム", e -> TAISEI.main(args));
            JButton haruButton = createModernButton("平光晴 － 広告詐欺削除ゲーム", e -> HARU.main(args));
            JButton HIDEButton = createModernButton("山北禿明 － すごろくゲーム", e -> HIDEAKI.main(args));
            JButton IBUKIButton = createModernButton("末吉伊吹 － クイズゲーム", e -> IBUKI.main(args));

            panel1.add(Box.createRigidArea(new Dimension(10, 20)));
            panel1.add(button);
            panel1.add(Box.createRigidArea(new Dimension(10, 20)));
            panel1.add(clockButton);
            panel1.add(Box.createRigidArea(new Dimension(10, 20)));
            panel1.add(diceButton);
            panel1.add(Box.createRigidArea(new Dimension(10, 20)));
            panel1.add(colorButton);
            panel1.add(Box.createRigidArea(new Dimension(10, 20)));
            panel1.add(infinityButton);
            panel1.add(Box.createRigidArea(new Dimension(10, 20)));
            panel1.add(calculatorButton);

            panel2.add(Box.createRigidArea(new Dimension(10, 43)));
            panel2.add(label4);
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
            mainPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Add a spacer panel between panel1 and panel2
            mainPanel.add(panel2);
            window.add(mainPanel);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }

    private static JButton createModernButton(String text, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(actionListener);
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Set a fixed width for all buttons
        Dimension buttonSize = new Dimension(300, button.getPreferredSize().height);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);

        return button;
    }

    @Deprecated
    private static JButton createButton(String text, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(actionListener);
        return button;
    }

    private static void showDigitalClock() {
        JFrame secondWindow = new JFrame("電子時計");
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
        JFrame infinityFrame = new JFrame("無限窓");
        infinityFrame.setSize(400, 300);
        infinityFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        infinityFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                SwingUtilities.invokeLater(() -> {
                    JFrame newFrame = new JFrame("無限窓");
                    newFrame.setSize(400, 300);
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    newFrame.addWindowListener(this);

                    JLabel label = new JLabel("消せるかな？");
                    label.setHorizontalAlignment(JLabel.CENTER);
                    newFrame.getContentPane().add(label);

                    newFrame.setVisible(true);
                });
            }
        });

        JLabel messageLabel = new JLabel("この窓からは逃れられない！");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        infinityFrame.getContentPane().add(messageLabel);

        infinityFrame.setVisible(true);
    }

    private static void showCalculator() {
        JFrame calcWindow = new JFrame("シンプル計算機");
        calcWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calcWindow.setSize(new Dimension(300, 200));
        calcWindow.setVisible(true);

        JPanel calcPanel = new JPanel();
        calcPanel.setLayout(new GridLayout(5, 1));
        calcWindow.add(calcPanel);

        JLabel titleLabel = new JLabel("シンプル計算機!");
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

        JLabel resultLabel = new JLabel("結果: ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        calcPanel.add(resultLabel);

        JButton calcButton = new JButton("計算");
        calcButton.addActionListener(e -> {
            double firstNumber = Double.parseDouble(firstField.getText());
            String operation = operationField.getText();
            double secondNumber = Double.parseDouble(secondField.getText());

            double result;
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
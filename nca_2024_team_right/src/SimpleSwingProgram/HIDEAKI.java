package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HIDEAKI extends JFrame {

    private final JLabel player1Label;
    private final JLabel player2Label;
    private final JLabel messageLabel;
    private final JButton rollButton;
    private int player1Position = 0;
    private int player2Position = 0;
    private int currentPlayer = 1; // 1: プレイヤー1, 2: プレイヤー2
    private int rollCount = 0;

    public HIDEAKI() {
        setTitle("すごろくゲーム");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // ラベルの作成
        player1Label = new JLabel("プレイヤー1: 0");
        player2Label = new JLabel("プレイヤー2: 0");
        messageLabel = new JLabel("プレイヤー1の番です");

        // ボタンの作成
        rollButton = new JButton("サイコロを振る");
        rollButton.addActionListener(e -> rollDice());

        // パネルにコンポーネントを追加
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1)); // 2行1列のGridLayout
        topPanel.add(player1Label);
        topPanel.add(player2Label);

        add(topPanel, BorderLayout.NORTH);
        add(messageLabel, BorderLayout.CENTER);
        add(rollButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HIDEAKI::new);
    }

    private void rollDice() {
        int diceRoll1 = rollDiceValue();
        int diceRoll2 = rollDiceValue();
        int totalRoll = diceRoll1 + diceRoll2;
        rollCount++;


        if (currentPlayer == 1) {
            player1Position += totalRoll;
            player1Label.setText("プレイヤー1: " + player1Position);
            messageLabel.setText("プレイヤー2の番です");
            currentPlayer = 2;
        } else {
            player2Position += totalRoll;
            player2Label.setText("プレイヤー2: " + player2Position);
            messageLabel.setText("プレイヤー1の番です");
            currentPlayer = 1;
        }

        messageLabel.setText(messageLabel.getText() + "<br>サイコロ: " + totalRoll + " (" + diceRoll1 + "+" + diceRoll2 + ")<br>" + "現在位置：" + (currentPlayer == 2 ? player1Position : player2Position) + " (" + rollCount + "回目)");
        messageLabel.setText("<html>" + messageLabel.getText() + "</html>");

        checkWin();
    }

    private void checkWin() {
        if (player1Position >= 100) {
            JOptionPane.showMessageDialog(this, "プレイヤー1の勝利！" + rollCount + "回でクリア！");
            resetGame();
        } else if (player2Position >= 100) {
            JOptionPane.showMessageDialog(this, "プレイヤー2の勝利！" + rollCount + "回でクリア！");
            resetGame();
        }
    }

    private void resetGame() {
        player1Position = 0;
        player2Position = 0;
        currentPlayer = 1;
        rollCount = 0;
        player1Label.setText("プレイヤー1: 0");
        player2Label.setText("プレイヤー2: 0");
        messageLabel.setText("プレイヤー1の番です");
    }

    private int rollDiceValue() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
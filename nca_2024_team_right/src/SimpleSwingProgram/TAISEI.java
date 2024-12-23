package SimpleSwingProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TAISEI extends JFrame {

    private final JLabel scoreLabel;
    private final JLabel timerLabel;
    private final JPanel gamePanel;
    private final JButton startButton;
    private int score = 0;
    private Point targetPosition;
    private int targetDiameter = 60; // 初期値を変更
    private boolean gameOver = false;
    private int remainingTime = 10;
    private Timer gameTimer;

    public TAISEI() {
        setTitle("Target game");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE
        setLayout(null);

        scoreLabel = new JLabel("Score: 0", JLabel.LEFT); // "score"を"Score:"に変更
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoreLabel.setBounds(10, 10, 200, 30);
        add(scoreLabel);

        timerLabel = new JLabel("Time: 10", JLabel.RIGHT); // "time limit"を"Time:"に変更
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        timerLabel.setBounds(getWidth() - 230, 10, 200, 30);
        add(timerLabel);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());

                if (gameOver) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 48)); // フォントを大きく、太字に
                    String gameOverMessage = "Game Over";
                    String scoreMessage = "Your Score: " + score;

                    // メッセージを中央に描画
                    int gameOverWidth = g.getFontMetrics().stringWidth(gameOverMessage);
                    int scoreWidth = g.getFontMetrics().stringWidth(scoreMessage);
                    g.drawString(gameOverMessage, getWidth() / 2 - gameOverWidth / 2, getHeight() / 2 - 50);
                    g.drawString(scoreMessage, getWidth() / 2 - scoreWidth / 2, getHeight() / 2 + 50);
                    return;
                }

                if (targetPosition != null) {
                    g.setColor(Color.RED);
                    g.fillOval(targetPosition.x, targetPosition.y, targetDiameter, targetDiameter);
                }
            }
        };
        gamePanel.setBounds(0, 0, getWidth(), getHeight());
        add(gamePanel);

        startButton = new JButton("Start Game"); // "game start"を"Start Game"に変更
        startButton.setFont(new Font("Arial", Font.BOLD, 32)); // ボタンのフォントを大きく、太字に
        startButton.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 25, 200, 50); // ボタンの位置を調整
        startButton.addActionListener(e -> startGame());
        gamePanel.add(startButton);

        gameTimer = new Timer(1000, e -> {
            if (!gameOver) {
                remainingTime--;
                timerLabel.setText("Time: " + remainingTime);
                if (remainingTime <= 0) {
                    gameOver = true;
                    gameTimer.stop(); // タイマーを停止
                    repaint();
                }
            }
        });

        gamePanel.addMouseListener(new MouseAdapter() { // gamePanelにMouseListenerを追加
            @Override
            public void mousePressed(MouseEvent e) {
                if (!gameOver && targetPosition != null && isClickInsideTarget(e.getPoint())) {
                    score++;
                    targetDiameter = Math.max(20, targetDiameter - 5);
                    spawnTarget();
                    scoreLabel.setText("Score: " + score);
                    repaint();
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TAISEI::new); // SwingのスレッドでGUIを作成
    }

    private void spawnTarget() {
        int x = (int) (Math.random() * (gamePanel.getWidth() - targetDiameter)); // gamePanelの幅を使用
        int y = (int) (Math.random() * (gamePanel.getHeight() - targetDiameter)); // gamePanelの高さを使用
        targetPosition = new Point(x, y);
        repaint();
    }

    private boolean isClickInsideTarget(Point clickPoint) {
        int centerX = targetPosition.x + targetDiameter / 2;
        int centerY = targetPosition.y + targetDiameter / 2;
        int radius = targetDiameter / 2;
        double distance = Math.sqrt(Math.pow(clickPoint.x - centerX, 2) + Math.pow(clickPoint.y - centerY, 2));
        return distance <= radius;
    }

    private void startGame() {
        score = 0;
        remainingTime = 10;
        gameOver = false;
        targetDiameter = 60;
        spawnTarget();
        gameTimer.start();
        startButton.setVisible(false);
        timerLabel.setText("Time: " + remainingTime); // タイマー表示を更新
        scoreLabel.setText("Score: " + score); // スコア表示を更新
    }
}
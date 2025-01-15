package SimpleSwingProgram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TAISEI extends JFrame {
    private int score = 0;
    private int remainingTime = 10;
    private boolean gameOver = false;
    private int targetDiameter = 60;
    private Point targetPosition = null;
    private Timer gameTimer;
    private JLabel scoreLabel, timerLabel;
    private JButton startButton;
    private JPanel gamePanel;

    public TAISEI() {
        setTitle("Target game");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE
        setLayout(null);

        scoreLabel = new JLabel("Score: 0", JLabel.LEFT);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoreLabel.setBounds(10, 10, 200, 30);
        add(scoreLabel);

        timerLabel = new JLabel("Time: 10", JLabel.RIGHT);
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
                    g.setFont(new Font("Arial", Font.BOLD, 48));
                    String gameOverMessage = "Game Over";
                    String scoreMessage = "Your Score: " + score;
                    String restartMessage = "Press ENTER to Restart";
                    

                    int gameOverWidth = g.getFontMetrics().stringWidth(gameOverMessage);
                    int scoreWidth = g.getFontMetrics().stringWidth(scoreMessage);
                    int restartMessageWidth = g.getFontMetrics().stringWidth(restartMessage);
                    int totalHeight = 50 + 50 + 50;  // 各メッセージ間の余白を含めた高さ
                    int startY = (getHeight() - totalHeight) / 2;
                    g.drawString(gameOverMessage, getWidth() / 2 - gameOverWidth / 2, startY); // ゲームオーバーメッセージ
                    g.drawString(scoreMessage, getWidth() / 2 - scoreWidth / 2, startY + 50);  // スコアメッセージ
                    g.drawString(restartMessage, getWidth() / 2 - restartMessageWidth / 2, startY + 100); // リスタートメッセージ

                    return;
                }

                if (targetPosition != null) {
                    g.setColor(Color.RED);
                    g.fillOval(targetPosition.x, targetPosition.y, targetDiameter, targetDiameter);
                }
            }
        };
        gamePanel.setBounds(0, 0, getWidth(), getHeight());
        gamePanel.setFocusable(true); // フォーカスを有効にしてキーボード入力を受け付ける
        add(gamePanel);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 32));
        startButton.setBounds(getWidth() / 2 - 100, getHeight() / 2 - 25, 200, 50);
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

        gamePanel.addMouseListener(new MouseAdapter() {
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

        // KeyListenerの追加
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && gameOver) {
                    startGame(); // ゲームをリスタート
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
        int x = (int) (Math.random() * (gamePanel.getWidth() - targetDiameter)); 
        int y = (int) (Math.random() * (gamePanel.getHeight() - targetDiameter)); 
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
        timerLabel.setText("Time: " + remainingTime);
        scoreLabel.setText("Score: " + score);
    }
}

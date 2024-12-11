package SimpleSwingProgram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


// Main class to launch the game
public class YUGA {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleMazeGame game = new SimpleMazeGame();
            game.setVisible(true);
        });
    }
}

// The actual game class
class SimpleMazeGame extends JFrame {
    private static final int CELL_SIZE = 40; // セルのサイズ
    private static final int ROWS = 10; // 行数
    private static final int COLS = 10; // 列数

    private int[][] maze = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
        {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
        {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private int playerRow = 1; // プレイヤーの初期行位置
    private int playerCol = 1; // プレイヤーの初期列位置

    public SimpleMazeGame() {
        setTitle("Simple Maze Game");
        setSize(COLS * CELL_SIZE, ROWS * CELL_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // キーボード入力を処理
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP && maze[playerRow - 1][playerCol] == 0) {
                    playerRow--;
                } else if (key == KeyEvent.VK_DOWN && maze[playerRow + 1][playerCol] == 0) {
                    playerRow++;
                } else if (key == KeyEvent.VK_LEFT && maze[playerRow][playerCol - 1] == 0) {
                    playerCol--;
                } else if (key == KeyEvent.VK_RIGHT && maze[playerRow][playerCol + 1] == 0) {
                    playerCol++;
                }
                repaint();
                checkWin();
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (maze[row][col] == 1) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        // プレイヤーを描画
        g.setColor(Color.BLUE);
        g.fillOval(playerCol * CELL_SIZE, playerRow * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // ゴールを描画
        g.setColor(Color.RED);
        g.fillRect((COLS - 2) * CELL_SIZE, (ROWS - 2) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private void checkWin() {
        if (playerRow == ROWS - 2 && playerCol == COLS - 2) {
            JOptionPane.showMessageDialog(this, "You Win!");
            System.exit(0);
        }
    }
}


	

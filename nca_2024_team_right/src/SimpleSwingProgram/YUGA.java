package SimpleSwingProgram;
/*
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class YUGA {
    public class SimpleMazeGame extends JFrame {
        private static final int CELL_SIZE = 30; // セルのサイズを小さく
        private static final int ROWS = 15; // 行数を増やして複雑に
        private static final int COLS = 15; // 列数を増やして複雑に

        // 第一ステージの迷路
        private int[][] mazeStage1 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1},
            {1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        // 第二ステージの迷路
        private int[][] mazeStage2 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        private int[][] obstacles = {
            {2, 2}, {5, 5}, {6, 7}, {8, 9}, {10, 6}, {12, 3}
        };

        private int playerRow = 1; // プレイヤーの初期行位置
        private int playerCol = 1; // プレイヤーの初期列位置
        private boolean isStage2 = false; // 第二ステージに進んだかどうか
        private boolean isReversed = false; // 移動反転フラグ

        public SimpleMazeGame() {
            setTitle("Simple Maze Game");
            setSize(COLS * CELL_SIZE, ROWS * CELL_SIZE);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);

            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (isReversed) {
                        // 移動反転（逆方向）
                        if (key == KeyEvent.VK_UP && mazeStage2[playerRow + 1][playerCol] == 0) {
                            playerRow++;
                        } else if (key == KeyEvent.VK_DOWN && mazeStage2[playerRow - 1][playerCol] == 0) {
                            playerRow--;
                        } else if (key == KeyEvent.VK_LEFT && mazeStage2[playerRow][playerCol + 1] == 0) {
                            playerCol++;
                        } else if (key == KeyEvent.VK_RIGHT && mazeStage2[playerRow][playerCol - 1] == 0) {
                            playerCol--;
                        }
                    } else {
                        // 第一ステージは通常移動
                        if (key == KeyEvent.VK_UP && mazeStage1[playerRow - 1][playerCol] == 0) {
                            playerRow--;
                        } else if (key == KeyEvent.VK_DOWN && mazeStage1[playerRow + 1][playerCol] == 0) {
                            playerRow++;
                        } else if (key == KeyEvent.VK_LEFT && mazeStage1[playerRow][playerCol - 1] == 0) {
                            playerCol--;
                        } else if (key == KeyEvent.VK_RIGHT && mazeStage1[playerRow][playerCol + 1] == 0) {
                            playerCol++;
                        }
                    }
                    repaint();
                    checkWin();
                    checkObstacle();
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            // 使用する迷路を選択
            int[][] currentMaze = isStage2 ? mazeStage2 : mazeStage1;

            // 迷路を描画
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if (currentMaze[row][col] == 1) {
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

            // 障害物を描画
            g.setColor(Color.YELLOW);
            for (int[] obstacle : obstacles) {
                g.fillRect(obstacle[1] * CELL_SIZE, obstacle[0] * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        private void checkWin() {
            if (playerRow == ROWS - 2 && playerCol == COLS - 2) {
                if (isStage2) {
                    JOptionPane.showMessageDialog(this, "You Win the Game!");
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(this, "You reached the goal! Stage 2 starts.");
                    isStage2 = true;
                    isReversed = true;
                    playerRow = ROWS - 2;
                    playerCol = COLS - 2;
                    repaint();
                }
            }
        }

        private void checkObstacle() {
            for (int[] obstacle : obstacles) {
                if (playerRow == obstacle[0] && playerCol == obstacle[1]) {
                    JOptionPane.showMessageDialog(this, "Game Over! You hit an obstacle.");
                    System.exit(0);
                }
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                SimpleMazeGame game = new SimpleMazeGame();
                game.setVisible(true);
            });
        }
    }
}
*/
 